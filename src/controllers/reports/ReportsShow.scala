package controllers.reports

import java.io.IOException
import javax.persistence.EntityManager
import javax.servlet.RequestDispatcher
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Report
import utils.DBUtil

@WebServlet("/reports/show")
@SerialVersionUID(1L)
class ReportsShow()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    val em = DBUtil.createEntityManager
    val r = em.find(classOf[Report], request.getParameter("id").toInt)
    em.close
    request.setAttribute("report", r)
    request.setAttribute("_token", request.getSession.getId)
    val rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp")
    rd.forward(request, response)
  }
}
