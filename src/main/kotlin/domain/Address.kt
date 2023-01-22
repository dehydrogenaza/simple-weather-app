package domain

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "addresses")
final class Address(
    val region: String?,
    val country: String,
    val adminArea: String?,
    val city: String?,
    @OneToOne(mappedBy = "address", cascade = [CascadeType.ALL])
    val location: Location
) : JpaPersistable(UUID.randomUUID())