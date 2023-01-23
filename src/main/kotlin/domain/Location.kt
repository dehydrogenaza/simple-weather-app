package domain

import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = DbTableNames.LOCATION)
class Location(
    @Column(name = "description")
    val description: String,

    @Column(name = "latitude")
    val latitude: Double?,

    @Column(name = "longitude")
    val longitude: Double?,

) : JpaPersistable(UUID.randomUUID()) {

    @OneToOne(cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER) //TODO: temporary solution before DTOs are implemented
//        fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    var address: Address? = null

    @OneToMany(mappedBy = "location",
        cascade = [CascadeType.ALL],
//        fetch = FetchType.LAZY)
        fetch = FetchType.EAGER) //TODO: temporary solution before DTOs are implemented
    val forecastDates = mutableListOf<ForecastDate>()
    override fun toString(): String {
        return "Location(description='$description', latitude=$latitude, longitude=$longitude, address=$address, forecastDates=$forecastDates)"
    }
}