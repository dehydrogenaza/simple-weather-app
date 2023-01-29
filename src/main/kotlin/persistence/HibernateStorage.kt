package persistence

import authentication.Credentials
import domain.*
import domain.weather.Forecast
import exceptions.WeatherAppAuthenticationException
import org.hibernate.Session
import org.hibernate.cfg.Configuration
import org.hibernate.query.Query
import org.hibernate.type.StringType

class HibernateStorage : IStorageSolution {
    companion object Config {
        private val credentials = Credentials().data
        private val db_url: String? = credentials["db_url"]
        private val db_username: String? = credentials["db_username"]
        private val db_password: String? = credentials["db_password"]
        init {
            check(db_url != null && db_username != null && db_password != null) {
                "Could not obtain credentials from $credentials"
            }
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

        sessionFactory.currentSession.use { session: Session? ->
            val transaction = session?.beginTransaction()
                ?: error("Hibernate: failed to get a Session.")
            val rows = try {
                session.createQuery("from $tableName", ofClass)
                    ?.resultList
            } catch (e: Exception) {
                transaction.rollback()
                throw e
            }

            return rows?.filterNotNull() ?: emptyList()
        }
    }

    override fun <T : JpaPersistable> read(ofClass: Class<T>,
                                           hql: String,
                                           params: Map<String, String>
    ): List<T> {
        //val tableName = ofClass.simpleName

        sessionFactory.currentSession.use { session: Session? ->
            val transaction = session?.beginTransaction()
                ?: error("Hibernate: failed to get a Session.")
            val rows = try {
                val query: Query<T> = session.createQuery(hql, ofClass).apply {
                    params.forEach{ (name, value) ->
                        setParameter(name, value, StringType.INSTANCE)
                    }
                }

                query.resultList.also { transaction.commit() }
            } catch (e: Exception) {
                transaction.rollback()
                throw e
            }

            return rows?.filterNotNull() ?: emptyList()
        }
    }

    override fun close() {
        sessionFactory.close()
    }

    private fun failGettingCredentials(key: String): Nothing =
        throw WeatherAppAuthenticationException(
            "Could not obtain credentials for key: $key"
        )
}