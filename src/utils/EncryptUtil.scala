package utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.xml.bind.DatatypeConverter

object EncryptUtil {
  def getPasswordEncrypt(plain_p: String, salt: String): String = {
    var ret = ""
    if (plain_p != null && !(plain_p == "")) {
      var bytes = null
      val password = plain_p + salt
      try {
        bytes = MessageDigest.getInstance("SHA-256").digest(password.getBytes)
        ret = DatatypeConverter.printHexBinary(bytes)
      } catch {
        case ex: NoSuchAlgorithmException =>

      }
    }
    ret
  }
}
