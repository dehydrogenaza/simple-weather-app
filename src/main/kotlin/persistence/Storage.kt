package persistence

import domain.JpaPersistable

object Storage : IStorageSolution {
    var dao: IStorageSolution? = null

    override fun add(persistable: JpaPersistable): Boolean {
        return dao?.add(persistable) ?: noInit()
    }

    private fun noInit(): Nothing = error("Storage was not initialized, set Storage.solution first.")
}