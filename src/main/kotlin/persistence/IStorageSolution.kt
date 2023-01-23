package persistence

import domain.ForecastDate
import domain.JpaPersistable
import domain.Location

interface IStorageSolution {
    fun add(persistable: JpaPersistable): Boolean
}