package utils

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

object DBUtil {
  private val PERSISTENCE_UNIT_NAME = "daily_report_system"
  private var emf = null

  def createEntityManager: Nothing = _getEntityManagerFactory.createEntityManager

  private def _getEntityManagerFactory = {
    if (emf == null) emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
    emf
  }
}
