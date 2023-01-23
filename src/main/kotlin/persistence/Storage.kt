package persistence

import domain.JpaPersistable

object Storage : IStorageSolution {
    var dao: IStorageSolution? = null

    override fun add(persistable: JpaPersistable): Boolean {
        return dao?.add(persistable) ?: noInit()
    }

    override fun <T : JpaPersistable> readAll(clazz: Class<T>): List<T> {
        return dao?.readAll(clazz) ?: noInit()
    }

    private fun noInit(): Nothing = error("Storage was not initialized, set Storage.solution first.")
}