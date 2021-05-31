package models

import java.sql.Date
import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.Lob
import javax.persistence.ManyToOne
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery
import javax.persistence.Table

@Table(name = "reports")
@NamedQueries(Array(new NamedQuery(name = "getAllReports", query = "SELECT r FROM Report AS r ORDER BY r.id DESC"), new NamedQuery(name = "getReportsCount", query = "SELECT COUNT(r) FROM Report AS r"), new NamedQuery(name = "getMyAllReports", query = "SELECT r FROM Report AS r WHERE r.employee = :employee ORDER BY r.id DESC"), new NamedQuery(name = "getMyReportsCount", query = "SELECT COUNT(r) FROM Report AS r WHERE r.employee = :employee")))
@Entity class Report {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY) private var id = null
  @ManyToOne
  @JoinColumn(name = "employee_id", nullable = false) private var employee = null
  @Column(name = "report_date", nullable = false) private var report_date = null
  @Column(name = "title", length = 255, nullable = false) private var title = null
  @Lob
  @Column(name = "content", nullable = false) private var content = null
  @Column(name = "created_at", nullable = false) private var created_at = null
  @Column(name = "updated_at", nullable = false) private var updated_at = null

  def getId: Integer = id

  def setId(id: Integer): Unit = {
    this.id = id
  }

  def getEmployee: Employee = employee

  def setEmployee(employee: Employee): Unit = {
    this.employee = employee
  }

  def getReport_date: Date = report_date

  def setReport_date(report_date: Date): Unit = {
    this.report_date = report_date
  }

  def getTitle: String = title

  def setTitle(title: String): Unit = {
    this.title = title
  }

  def getContent: String = content

  def setContent(content: String): Unit = {
    this.content = content
  }

  def getCreated_at: Timestamp = created_at

  def setCreated_at(created_at: Timestamp): Unit = {
    this.created_at = created_at
  }

  def getUpdated_at: Timestamp = updated_at

  def setUpdated_at(updated_at: Timestamp): Unit = {
    this.updated_at = updated_at
  }
}
