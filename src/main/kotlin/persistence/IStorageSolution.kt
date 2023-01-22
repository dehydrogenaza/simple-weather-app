package persistence

import domain.Location

interface IStorageSolution {
    fun add(location: Location): Boolean
}