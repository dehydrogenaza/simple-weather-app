package domain

import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "locations")
class Location(
    val description: String,
    val latitude: Double,
    val longitude: Double,
    //val forecastDates: List<String>
) : JpaPersistable(UUID.randomUUID()) {
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    var address: Address? = null
}