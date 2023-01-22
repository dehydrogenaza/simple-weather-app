package persistence

import domain.Location

object Storage : IStorageSolution {
    var dao: IStorageSolution? = null

    override fun add(location: Location): Boolean {
        return dao?.add(location) ?: noInit()
    }

    private fun noInit(): Nothing = error("Storage was not initialized, set Storage.solution first.")
}