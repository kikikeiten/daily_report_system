package controllers.employees

import java.io.IOException
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
import models.validators.EmployeeValidator
import utils.DBUtil
import utils.EncryptUtil

import java.util.List

@WebServlet("/employees/update")
@SerialVersionUID(1L)
class EmployeesUpdateServlet()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doPost(request: Nothing, response: Nothing): Unit = {
    val _token = request.getParameter("_token").asInstanceOf[String]
    if (_token != null && _token == request.getSession.getId) {
      val em = DBUtil.createEntityManager
      val e = em.find(classOf[Employee], request.getSession.getAttribute("employee_id").asInstanceOf[Integer])
      var code_duplicate_check = true
      if (e.getCode == request.getParameter("code")) code_duplicate_check = false
      else e.setCode(request.getParameter("code"))
      var password_check_flag = true
      val password = request.getParameter("password")
      if (password == null || password == "") password_check_flag = false
      else e.setPassword(EncryptUtil.getPasswordEncrypt(password, this.getServletContext.getAttribute("salt").asInstanceOf[String]))
      e.setName(request.getParameter("name"))
      e.setAdmin_flag(request.getParameter("admin_flag").toInt)
      e.setUpdated_at(new Timestamp(System.currentTimeMillis))
      e.setDelete_flag(0)
      val errors = EmployeeValidator.validate(e, code_duplicate_check, password_check_flag)
      if (errors.size > 0) {
        em.close
        request.setAttribute("_token", request.getSession.getId)
        request.setAttribute("employee", e)
        request.setAttribute("errors", errors)
        val rd = request.getRequestDispatcher("/WEB-INF/views/employees/edit.jsp")
        rd.forward(request, response)
      }
      else {
        em.getTransaction.begin
        em.getTransaction.commit
        em.close
        request.getSession.setAttribute("flush", "更新が完了しました。")
        request.getSession.removeAttribute("employee_id")
        response.sendRedirect(request.getContextPath + "/employees/index")
      }
    }
  }
}
