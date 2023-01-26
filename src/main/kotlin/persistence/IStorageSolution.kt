package persistence

import domain.JpaPersistable

interface IStorageSolution {
    fun add(persistable: JpaPersistable): Boolean
    fun <T : JpaPersistable> readAll(ofClass: Class<T>): List<T>
}