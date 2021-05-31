package controllers.employees

import java.io.IOException
import java.sql.Timestamp
import javax.persistence.EntityManager
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Employee
import utils.DBUtil

@WebServlet("/employees/destroy")
@SerialVersionUID(1L)
class EmployeesDestroy()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doPost(request: Nothing, response: Nothing): Unit = {
    val _token = request.getParameter("_token").asInstanceOf[String]
    if (_token != null && _token == request.getSession.getId) {
      val em = DBUtil.createEntityManager
      val e = em.find(classOf[Employee], request.getSession.getAttribute("employee_id").asInstanceOf[Integer])
      e.setDelete_flag(1)
      e.setUpdated_at(new Timestamp(System.currentTimeMillis))
      em.getTransaction.begin
      em.getTransaction.commit
      em.close
      request.getSession.setAttribute("flush", "削除が完了しました。")
      response.sendRedirect(request.getContextPath + "/employees/index")
    }
  }
}
