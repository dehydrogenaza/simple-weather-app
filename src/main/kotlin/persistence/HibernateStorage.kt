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
            configure()
            setProperty("hibernate.connection.url", db_url)
            setProperty("hibernate.connection.username", db_username)
            setProperty("hibernate.connection.password", db_password)
            addAnnotatedClass(Location::class.java)
            addAnnotatedClass(Address::class.java)
            addAnnotatedClass(ForecastDate::class.java)
            addAnnotatedClass(Forecast::class.java)
        }.buildSessionFactory()
    }

    override fun add(location: Location): Boolean {
        sessionFactory.currentSession.use { session: Session? ->
            val transaction = session?.beginTransaction()
                ?: error("Hibernate: failed to get a Session.")
            try {
                session.persist(location)
                transaction.commit()
                return true
            } catch (e: Exception) {
                transaction.rollback()
                throw e
            }
        }
    }
}