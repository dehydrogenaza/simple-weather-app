package domain

import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "locations")
class Location(
    @Column(name = "description")
    val description: String,

    @Column(name = "latitude")
    val latitude: Double?,

    @Column(name = "longitude")
    val longitude: Double?,

    @OneToMany(mappedBy = "location",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY)
    val forecastDates: MutableList<ForecastDate>

) : JpaPersistable(UUID.randomUUID()) {
    @OneToOne(cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    var address: Address? = null
}