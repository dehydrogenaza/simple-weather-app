package persistence

import domain.JpaPersistable

object Storage : IStorageSolution {
    var dao: IStorageSolution? = null

    override fun add(persistable: JpaPersistable): Boolean {
        return dao?.add(persistable) ?: noInit()
    }

    override fun <T : JpaPersistable> readAll(ofClass: Class<T>): List<T> {
        return dao?.readAll(ofClass) ?: noInit()
    }

    override fun <T : JpaPersistable> read(ofClass: Class<T>,
                                           hql: String,
                                           hqlParams: Map<String, String>
    ): List<T> {
        return dao?.read(ofClass, hql, hqlParams) ?: noInit()
    }

    override fun <T : JpaPersistable> delete(ofClass: Class<T>,
                                             hql: String,
                                             hqlParams: Map<String, String>,
                                             confirmationCallback: (List<T>) -> T?
    ): Boolean {
        return dao?.delete(ofClass, hql, hqlParams, confirmationCallback) ?: noInit()
    }

    override fun close() {
        dao?.close()
    }

    private fun noInit(): Nothing = error("Storage was not initialized, set Storage.dao first.")
}