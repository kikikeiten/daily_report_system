package controllers.reports

import java.io.IOException
import javax.persistence.EntityManager
import javax.servlet.RequestDispatcher
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Employee
import models.Report
import utils.DBUtil

@WebServlet("/reports/edit")
@SerialVersionUID(1L)
class ReportsEdit()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    val em = DBUtil.createEntityManager
    val r = em.find(classOf[Report], request.getParameter("id").toInt)
    em.close
    val login_employee = request.getSession.getAttribute("login_employee").asInstanceOf[Employee]
    if (r != null && (login_employee.getId eq r.getEmployee.getId)) {
      request.setAttribute("report", r)
      request.setAttribute("_token", request.getSession.getId)
      request.getSession.setAttribute("report_id", r.getId)
    }
    val rd = request.getRequestDispatcher("/WEB-INF/views/reports/edit.jsp")
    rd.forward(request, response)
  }
}
