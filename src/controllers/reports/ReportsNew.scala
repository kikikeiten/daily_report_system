package controllers.reports

import java.io.IOException
import java.sql.Date
import javax.servlet.RequestDispatcher
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Report

@WebServlet("/reports/new")
@SerialVersionUID(1L)
class ReportsNew()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    request.setAttribute("_token", request.getSession.getId)
    val r = new Report
    r.setReport_date(new Date(System.currentTimeMillis))
    request.setAttribute("report", r)
    val rd = request.getRequestDispatcher("/WEB-INF/views/reports/new.jsp")
    rd.forward(request, response)
  }
}
