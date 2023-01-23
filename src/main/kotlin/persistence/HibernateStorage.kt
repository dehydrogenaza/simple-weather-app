package persistence

import domain.*
import domain.weather.Forecast
import org.hibernate.Session
import org.hibernate.cfg.Configuration
import java.util.*

class HibernateStorage : IStorageSolution {
    companion object Config {

        private val dataBundle = ResourceBundle.getBundle("credentials")
        private val db_url: String = dataBundle.getString("db_url")
        private val db_username: String = dataBundle.getString("db_username")
        private val db_password: String = dataBundle.getString("db_password")

        private val sessionFactory = Configuration().apply {
            configure("hibernate.cfg.xml")
            setProperty("hibernate.connection.url", db_url)
            setProperty("hibernate.connection.username", db_username)
            setProperty("hibernate.connection.password", db_password)
            addAnnotatedClass(Location::class.java)
            addAnnotatedClass(Address::class.java)
            addAnnotatedClass(ForecastDate::class.java)
            addAnnotatedClass(Forecast::class.java)
        }.buildSessionFactory()
    }

    override fun add(persistable: JpaPersistable): Boolean {
        sessionFactory.currentSession.use { session: Session? ->
            val transaction = session?.beginTransaction()
                ?: error("Hibernate: failed to get a Session.")
            try {
                session.persist(persistable)
                transaction.commit()
                return true
            } catch (e: Exception) {
                transaction.rollback()
                throw e
            }
        }
    }

    override fun <T : JpaPersistable> readAll(clazz: Class<T>): List<T> {
        val tableName = clazz.simpleName

        sessionFactory.currentSession.use { session: Session? ->
            val transaction = session?.beginTransaction()
                ?: error("Hibernate: failed to get a Session.")
            val rows = try {
                session.createQuery("from $tableName", clazz)
                    ?.resultList
            } catch (e: Exception) {
                transaction.rollback()
                throw e
            }

            return rows?.filterNotNull() ?: emptyList()
        }
    }
}