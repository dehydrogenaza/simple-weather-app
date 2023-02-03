package domain.location

import domain.DbTableNames
import domain.JpaPersistable
import domain.weather.ForecastDate
import ui.IDisplayableList
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = DbTableNames.LOCATION)
class Location(
    @Column(name = "description")
    val description: String,

    @Column(name = "accuweatherLocationKey")
    val accuweatherLocationKey: String,

    @Column(name = "latitude")
    val latitude: Double?,

    @Column(name = "longitude")
    val longitude: Double?,

    ) : JpaPersistable(UUID.randomUUID()), IDisplayableList {
    @Column(name = "hidden")
    var hidden: Boolean = false

    @OneToOne(
        cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER
    ) //TODO: temporary solution before DTOs are implemented
//        fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    var address: Address? = null

    @OneToMany(
        mappedBy = "location",
        cascade = [CascadeType.ALL],
//        fetch = FetchType.LAZY)
        fetch = FetchType.EAGER
    ) //TODO: temporary solution before DTOs are implemented
    val forecastDates = mutableListOf<ForecastDate>()

    //    override fun toString(): String {
//        return "Location(description='$description', latitude=$latitude, longitude=$longitude, address=$address, forecastDates=$forecastDates)"
//    }
    override fun toString(): String =
        if (isHidden()) "(hidden) " else "" +
                "${description.uppercase()} (lat. $latitude, long. $longitude):\n\t" +
                if (address != null) "${address?.city}, ${address?.adminArea}, ${address?.country}" else "(missing details)"

    override fun isHidden(): Boolean = hidden
}