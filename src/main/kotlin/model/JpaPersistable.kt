package model

import java.util.UUID
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.Version

@MappedSuperclass
abstract class JpaPersistable (
    uuid: UUID,
    @Version protected val version: Int
) {
    @Id
    protected val id: String = uuid.toString()

    //THIS IS NOT SO SIMPLE/OBVIOUS!
    //Read:
    //https://web.archive.org/web/20171211235806/http://www.onjava.com/pub/a/onjava/2006/09/13/dont-let-hibernate-steal-your-identity.html
    //https://vmaks.github.io/2019/11/27/how-to-implement-equals-hashcode-for-kotlin-entity/
    //https://kotlinexpertise.com/hibernate-with-kotlin-spring-boot/
    final override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != other.javaClass) return false

        other as JpaPersistable

        return if (id.isEmpty()) false else id == other.id
    }

    final override fun hashCode(): Int = id.hashCode()
}