package controllers.reports

import java.io.IOException
import java.sql.Date
import java.sql.Timestamp
import java.util
import javax.persistence.EntityManager
import javax.servlet.RequestDispatcher
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Report
import models.validators.ReportValidator
import utils.DBUtil

import java.util.List

@WebServlet("/reports/update")
@SerialVersionUID(1L)
class ReportsUpdate()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doPost(request: Nothing, response: Nothing): Unit = {
    val _token = request.getParameter("_token").asInstanceOf[String]
    if (_token != null && _token == request.getSession.getId) {
      val em = DBUtil.createEntityManager
      val r = em.find(classOf[Report], request.getSession.getAttribute("report_id").asInstanceOf[Integer])
      r.setReport_date(Date.valueOf(request.getParameter("report_date")))
      r.setTitle(request.getParameter("title"))
      r.setContent(request.getParameter("content"))
      r.setUpdated_at(new Timestamp(System.currentTimeMillis))
      val errors = ReportValidator.validate(r)
      if (errors.size > 0) {
        em.close
        request.setAttribute("_token", request.getSession.getId)
        request.setAttribute("report", r)
        request.setAttribute("errors", errors)
        val rd = request.getRequestDispatcher("/WEB-INF/views/reports/edit.jsp")
        rd.forward(request, response)
      }
      else {
        em.getTransaction.begin
        em.getTransaction.commit
        em.close
        request.getSession.setAttribute("flush", "更新が完了しました。")
        request.getSession.removeAttribute("report_id")
        response.sendRedirect(request.getContextPath + "/reports/index")
      }
    }
  }
}
