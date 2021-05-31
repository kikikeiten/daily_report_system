package controllers.employees

import java.io.IOException
import javax.servlet.RequestDispatcher
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Employee

@WebServlet("/employees/new")
@SerialVersionUID(1L)
class EmployeesNew()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    request.setAttribute("_token", request.getSession.getId)
    request.setAttribute("employee", new Employee)
    val rd = request.getRequestDispatcher("/WEB-INF/views/employees/new.jsp")
    rd.forward(request, response)
  }
}
