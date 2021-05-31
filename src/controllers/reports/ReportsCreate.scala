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
import models.Employee
import models.Report
import models.validators.ReportValidator
import utils.DBUtil

import java.util.List

@WebServlet("/reports/create")
@SerialVersionUID(1L)
class ReportsCreate()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doPost(request: Nothing, response: Nothing): Unit = {
    val _token = request.getParameter("_token").asInstanceOf[String]
    if (_token != null && _token == request.getSession.getId) {
      val em = DBUtil.createEntityManager
      val r = new Report
      r.setEmployee(request.getSession.getAttribute("login_employee").asInstanceOf[Employee])
      var report_date = new Date(System.currentTimeMillis)
      val rd_str = request.getParameter("report_date")
      if (rd_str != null && !(rd_str == "")) report_date = Date.valueOf(request.getParameter("report_date"))
      r.setReport_date(report_date)
      r.setTitle(request.getParameter("title"))
      r.setContent(request.getParameter("content"))
      val currentTime = new Timestamp(System.currentTimeMillis)
      r.setCreated_at(currentTime)
      r.setUpdated_at(currentTime)
      val errors = ReportValidator.validate(r)
      if (errors.size > 0) {
        em.close
        request.setAttribute("_token", request.getSession.getId)
        request.setAttribute("report", r)
        request.setAttribute("errors", errors)
        val rd = request.getRequestDispatcher("/WEB-INF/views/reports/new.jsp")
        rd.forward(request, response)
      }
      else {
        em.getTransaction.begin
        em.persist(r)
        em.getTransaction.commit
        em.close
        request.getSession.setAttribute("flush", "登録が完了しました。")
        response.sendRedirect(request.getContextPath + "/reports/index")
      }
    }
  }
}
