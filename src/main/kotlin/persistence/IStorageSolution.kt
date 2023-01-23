package persistence

import domain.JpaPersistable

interface IStorageSolution {
    fun add(persistable: JpaPersistable): Boolean
    fun <T : JpaPersistable> readAll(clazz: Class<T>): List<T>
}