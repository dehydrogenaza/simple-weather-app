package persistence

import authentication.Credentials
import domain.*
import domain.weather.Forecast
import exceptions.WeatherAppAuthenticationException
import org.hibernate.Session
import org.hibernate.cfg.Configuration
import org.hibernate.query.Query
import org.hibernate.type.StringType
import ui.Txt
import ui.display

class HibernateStorage : IStorageSolution {
    companion object Config {
        private val credentials = Credentials().data
        private val db_url: String? = credentials["db_url"]
        private val db_username: String? = credentials["db_username"]
        private val db_password: String? = credentials["db_password"]

        init {
            if (db_url == null || db_username == null || db_password == null) failGettingCredentials()
        }

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

        private fun failGettingCredentials(): Nothing =
            throw WeatherAppAuthenticationException("Could not obtain credentials from $credentials")
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

    override fun <T : JpaPersistable> readAll(ofClass: Class<T>): List<T> {
        val tableName = ofClass.simpleName

        return read(ofClass, "FROM $tableName")
    }

    override fun <T : JpaPersistable> read(
        ofClass: Class<T>,
        hql: String,
        hqlParams: Map<String, String>
    ): List<T> {
        sessionFactory.currentSession.use { session: Session? ->
            val transaction = session?.beginTransaction()
                ?: error("Hibernate: failed to get a Session.")
            val rows = try {
                listFromQuery(session, ofClass, hql, hqlParams).also { transaction.commit() }
            } catch (e: Exception) {
                transaction.rollback()
                throw e
            }

            return rows
        }
    }

    private fun <T : JpaPersistable> listFromQuery(
        session: Session,
        ofClass: Class<T>,
        hql: String,
        hqlParams: Map<String, String>
    ): List<T> {
        val query: Query<T> = session.createQuery(hql, ofClass).apply {
            hqlParams.forEach { (name, value) ->
                setParameter(name, value, StringType.INSTANCE)
            }
        }
        return query.resultList ?: emptyList()
    }

    override fun <T : JpaPersistable> delete(
        ofClass: Class<T>,
        hql: String,
        hqlParams: Map<String, String>,
        confirmationCallback: (List<T>) -> T?
    ): Boolean {
        sessionFactory.currentSession.use { session: Session? ->
            val transaction = session?.beginTransaction()
                ?: error("Hibernate: failed to get a Session.")
            try {
                val existing = listFromQuery(session, ofClass, hql, hqlParams)
                val persistable: T = if (existing.isEmpty()) {
                    Txt.LOCATION_NOT_FOUND.display("...")
                    return false
                } else {
                    confirmationCallback(existing) ?: return false
                }
                session.delete(persistable)
                transaction.commit()
                return true
            } catch (e: Exception) {
                println(e)
                transaction.rollback()
                throw e
            }
        }
    }

    override fun close() {
        sessionFactory.close()
    }
}