package filters

import java.io.IOException
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession
import models.Employee

@WebFilter("/*") class LoginFilter()

  extends Nothing {
  def destroy(): Unit = {
  }

  @throws[IOException]
  @throws[ServletException]
  def doFilter(request: Nothing, response: Nothing, chain: Nothing): Unit = {
    val context_path = request.asInstanceOf[Nothing].getContextPath
    val servlet_path = request.asInstanceOf[Nothing].getServletPath
    if (!servlet_path.matches("/css.*")) {
      val session = request.asInstanceOf[Nothing].getSession
      val e = session.getAttribute("login_employee").asInstanceOf[Employee]
      if (!(servlet_path == "/login")) {
        if (e == null) {
          response.asInstanceOf[Nothing].sendRedirect(context_path + "/login")
          return
        }
        if (servlet_path.matches("/employees.*") && (e.getAdmin_flag eq 0)) {
          response.asInstanceOf[Nothing].sendRedirect(context_path + "/")
          return
        }
      }
      else {
        if (e != null) {
          response.asInstanceOf[Nothing].sendRedirect(context_path + "/")
          return
        }
      }
    }
    chain.doFilter(request, response)
  }

  @throws[ServletException]
  def init(fConfig: Nothing): Unit = {
  }
}
