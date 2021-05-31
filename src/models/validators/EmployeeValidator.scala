package models.validators

import java.util
import javax.persistence.EntityManager
import models.Employee
import utils.DBUtil

import java.util.{ArrayList, List}

object EmployeeValidator {
  def validate(e: Employee, code_duplicate_check_flag: Boolean, password_check_flag: Boolean): util.List[String] = {
    val errors = new util.ArrayList[String]
    val code_error = _validateCode(e.getCode, code_duplicate_check_flag)
    if (!(code_error == "")) errors.add(code_error)
    val name_error = _validateName(e.getName)
    if (!(name_error == "")) errors.add(name_error)
    val password_error = _validatePassword(e.getPassword, password_check_flag)
    if (!(password_error == "")) errors.add(password_error)
    errors
  }

  private def _validateCode(code: String, code_duplicate_check_flag: Boolean): String = { // 必須入力チェック
    if (code == null || code == "") return "社員番号を入力してください。"
    if (code_duplicate_check_flag) {
      val em = DBUtil.createEntityManager
      val employees_count = em.createNamedQuery("checkRegisteredCode", classOf[Long]).setParameter("code", code).getSingleResult.asInstanceOf[Long]
      em.close
      if (employees_count > 0) return "入力された社員番号の情報はすでに存在しています。"
    }
    ""
  }

  private def _validateName(name: String): String = {
    if (name == null || name == "") return "氏名を入力してください。"
    ""
  }

  private def _validatePassword(password: String, password_check_flag: Boolean): String = { // パスワードを変更する場合のみ実行
    if (password_check_flag && (password == null || password == "")) return "パスワードを入力してください。"
    ""
  }
}
