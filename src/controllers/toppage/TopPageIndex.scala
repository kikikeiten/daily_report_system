package controllers.toppage

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
import models.Report
import utils.DBUtil

import java.util.List

@WebServlet("/index.html")
@SerialVersionUID(1L)
class TopPageIndex()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    val em = DBUtil.createEntityManager
    val login_employee = request.getSession.getAttribute("login_employee").asInstanceOf[Employee]
    var page = 0
    try page = request.getParameter("page").toInt
    catch {
      case e: Exception =>
        page = 1
    }
    val reports = em.createNamedQuery("getMyAllReports", classOf[Report]).setParameter("employee", login_employee).setFirstResult(15 * (page - 1)).setMaxResults(15).getResultList
    val reports_count = em.createNamedQuery("getMyReportsCount", classOf[Long]).setParameter("employee", login_employee).getSingleResult.asInstanceOf[Long]
    em.close
    request.setAttribute("reports", reports)
    request.setAttribute("reports_count", reports_count)
    request.setAttribute("page", page)
    if (request.getSession.getAttribute("flush") != null) {
      request.setAttribute("flush", request.getSession.getAttribute("flush"))
      request.getSession.removeAttribute("flush")
    }
    val rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp")
    rd.forward(request, response)
  }
}
