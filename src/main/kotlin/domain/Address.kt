package domain

import java.util.*
import javax.persistence.*


@Entity
@Table(name = DbTableNames.ADDRESS)
final class Address(
    @Column(name = "region")
    val region: String?,

    @Column(name = "country")
    val country: String?,

    @Column(name = "admin_area")
    val adminArea: String?,

    @Column(name = "city")
    val city: String?,

    @OneToOne(mappedBy = "address", cascade = [CascadeType.ALL])
    val location: Location

) : JpaPersistable(UUID.randomUUID())