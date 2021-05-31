package controllers.login

import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/logout")
@SerialVersionUID(1L)
class Logout()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    request.getSession.removeAttribute("login_employee")
    request.getSession.setAttribute("flush", "ログアウトしました。")
    response.sendRedirect(request.getContextPath + "/login")
  }
}
