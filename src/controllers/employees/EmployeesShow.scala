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

@WebServlet("/employees/show")
@SerialVersionUID(1L)
class EmployeesShow()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    val em = DBUtil.createEntityManager
    val e = em.find(classOf[Employee], request.getParameter("id").toInt)
    em.close
    request.setAttribute("employee", e)
    val rd = request.getRequestDispatcher("/WEB-INF/views/employees/show.jsp")
    rd.forward(request, response)
  }
}
