package models.validators

import java.util
import models.Report

import java.util.{ArrayList, List}

object ReportValidator {
  def validate(r: Report): util.List[String] = {
    val errors = new util.ArrayList[String]
    val title_error = _validateTitle(r.getTitle)
    if (!(title_error == "")) errors.add(title_error)
    val content_error = _validateContent(r.getContent)
    if (!(content_error == "")) errors.add(content_error)
    errors
  }

  private def _validateTitle(title: String): String = {
    if (title == null || title == "") return "タイトルを入力してください。"
    ""
  }

  private def _validateContent(content: String): String = {
    if (content == null || content == "") return "内容を入力してください。"
    ""
  }
}
