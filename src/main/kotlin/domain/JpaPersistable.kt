package domain

import java.util.UUID
import javax.persistence.*

@MappedSuperclass
abstract class JpaPersistable (uuid: UUID) {
    @Id
    @Column(name = "id", length = 36)
    val uuid: String = uuid.toString()

    @Version
    @Column(name = "version")
    protected val version: Int? = null

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

        return if (uuid.isEmpty()) false else uuid == other.uuid
    }

    final override fun hashCode(): Int = uuid.hashCode()
}