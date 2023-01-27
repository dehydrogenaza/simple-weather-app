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

    override fun close() {
        dao?.close()
    }

    private fun noInit(): Nothing = error("Storage was not initialized, set Storage.dao first.")
}