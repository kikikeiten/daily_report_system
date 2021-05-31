package listeners

import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.util
import java.util.{Iterator, Properties}
import javax.servlet.ServletContext
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener

@WebListener class PropertiesListener()

  extends Nothing {
  def contextDestroyed(arg0: Nothing): Unit = {
  }

  def contextInitialized(arg0: Nothing): Unit = {
    val context = arg0.getServletContext
    val path = context.getRealPath("/META-INF/application.properties")
    try {
      val is = new FileInputStream(path)
      val properties = new Properties
      properties.load(is)
      is.close()
      val pit = properties.stringPropertyNames.iterator
      while ( {
        pit.hasNext
      }) {
        val pname = pit.next
        context.setAttribute(pname, properties.getProperty(pname))
      }
    } catch {
      case e: FileNotFoundException =>

      case e: IOException =>

    }
  }
}
