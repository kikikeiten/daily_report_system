package models

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery
import javax.persistence.Table

@Table(name = "employees")
@NamedQueries(Array(new NamedQuery(name = "getAllEmployees", query = "SELECT e FROM Employee AS e ORDER BY e.id DESC"), new NamedQuery(name = "getEmployeesCount", query = "SELECT COUNT(e) FROM Employee AS e"), new NamedQuery(name = "checkRegisteredCode", query = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :code"), new NamedQuery(name = "checkLoginCodeAndPassword", query = "SELECT e FROM Employee AS e WHERE e.delete_flag = 0 AND e.code = :code AND e.password = :pass")))
@Entity class Employee {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY) private var id = null
  @Column(name = "code", nullable = false, unique = true) private var code = null
  @Column(name = "name", nullable = false) private var name = null
  @Column(name = "password", length = 64, nullable = false) private var password = null
  @Column(name = "admin_flag", nullable = false) private var admin_flag = null
  @Column(name = "created_at", nullable = false) private var created_at = null
  @Column(name = "updated_at", nullable = false) private var updated_at = null
  @Column(name = "delete_flag", nullable = false) private var delete_flag = null

  def getId: Integer = id

  def setId(id: Integer): Unit = {
    this.id = id
  }

  def getCode: String = code

  def setCode(code: String): Unit = {
    this.code = code
  }

  def getName: String = name

  def setName(name: String): Unit = {
    this.name = name
  }

  def getPassword: String = password

  def setPassword(password: String): Unit = {
    this.password = password
  }

  def getAdmin_flag: Integer = admin_flag

  def setAdmin_flag(admin_flag: Integer): Unit = {
    this.admin_flag = admin_flag
  }

  def getCreated_at: Timestamp = created_at

  def setCreated_at(created_at: Timestamp): Unit = {
    this.created_at = created_at
  }

  def getUpdated_at: Timestamp = updated_at

  def setUpdated_at(updated_at: Timestamp): Unit = {
    this.updated_at = updated_at
  }

  def getDelete_flag: Integer = delete_flag

  def setDelete_flag(delete_flag: Integer): Unit = {
    this.delete_flag = delete_flag
  }
}
