package controllers.employees

import java.io.IOException
import javax.persistence.EntityManager
import javax.servlet.RequestDispatcher
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Employee
import utils.DBUtil

@WebServlet("/employees/edit")
@SerialVersionUID(1L)
class EmployeesEdit()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    val em = DBUtil.createEntityManager
    val e = em.find(classOf[Employee], request.getParameter("id").toInt)
    em.close
    request.setAttribute("employee", e)
    request.setAttribute("_token", request.getSession.getId)
    request.getSession.setAttribute("employee_id", e.getId)
    val rd = request.getRequestDispatcher("/WEB-INF/views/employees/edit.jsp")
    rd.forward(request, response)
  }
}
