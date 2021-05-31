package controllers.employees

import java.io.IOException
import java.util
import javax.persistence.EntityManager
import javax.servlet.RequestDispatcher
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Employee
import utils.DBUtil

import java.util.List

@WebServlet("/employees/index")
@SerialVersionUID(1L)
class EmployeesIndex()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    val em = DBUtil.createEntityManager
    var page = 1
    try page = request.getParameter("page").toInt
    catch {
      case e: NumberFormatException =>

    }
    val employees = em.createNamedQuery("getAllEmployees", classOf[Employee]).setFirstResult(15 * (page - 1)).setMaxResults(15).getResultList
    val employees_count = em.createNamedQuery("getEmployeesCount", classOf[Long]).getSingleResult.asInstanceOf[Long]
    em.close
    request.setAttribute("employees", employees)
    request.setAttribute("employees_count", employees_count)
    request.setAttribute("page", page)
    if (request.getSession.getAttribute("flush") != null) {
      request.setAttribute("flush", request.getSession.getAttribute("flush"))
      request.getSession.removeAttribute("flush")
    }
    val rd = request.getRequestDispatcher("/WEB-INF/views/employees/index.jsp")
    rd.forward(request, response)
  }
}
