package controllers.login

import java.io.IOException
import javax.persistence.EntityManager
import javax.persistence.NoResultException
import javax.servlet.RequestDispatcher
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Employee
import utils.DBUtil
import utils.EncryptUtil

@WebServlet("/login")
@SerialVersionUID(1L)
class Login()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    request.setAttribute("_token", request.getSession.getId)
    request.setAttribute("hasError", false)
    if (request.getSession.getAttribute("flush") != null) {
      request.setAttribute("flush", request.getSession.getAttribute("flush"))
      request.getSession.removeAttribute("flush")
    }
    val rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp")
    rd.forward(request, response)
  }

  @throws[ServletException]
  @throws[IOException]
  protected def doPost(request: Nothing, response: Nothing): Unit = {
    var check_result = false
    val code = request.getParameter("code")
    val plain_pass = request.getParameter("password")
    var e = null
    if (code != null && !(code == "") && plain_pass != null && !(plain_pass == "")) {
      val em = DBUtil.createEntityManager
      val password = EncryptUtil.getPasswordEncrypt(plain_pass, this.getServletContext.getAttribute("salt").asInstanceOf[String])
      try e = em.createNamedQuery("checkLoginCodeAndPassword", classOf[Employee]).setParameter("code", code).setParameter("pass", password).getSingleResult
      catch {
        case ex: Nothing =>

      }
      em.close
      if (e != null) check_result = true
    }
    if (!check_result) {
      request.setAttribute("_token", request.getSession.getId)
      request.setAttribute("hasError", true)
      request.setAttribute("code", code)
      val rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp")
      rd.forward(request, response)
    }
    else {
      request.getSession.setAttribute("login_employee", e)
      request.getSession.setAttribute("flush", "ログインしました。")
      response.sendRedirect(request.getContextPath + "/")
    }
  }
}
