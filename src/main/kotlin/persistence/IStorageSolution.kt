package persistence

import domain.JpaPersistable

interface IStorageSolution {
    fun add(persistable: JpaPersistable): Boolean
    fun <T : JpaPersistable> readAll(ofClass: Class<T>): List<T>
    fun <T : JpaPersistable> read(ofClass: Class<T>,
                                  hql: String,
                                  params: Map<String, String> = emptyMap()
    ): List<T>
    fun close()
}