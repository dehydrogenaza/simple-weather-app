package domain

import domain.weather.Forecast
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*



@Entity
@Table(name = DbTableNames.FORECAST_DATE)
class ForecastDate(
    @Column(name = "start_date")
    val startDate: LocalDateTime,

    @Column(name = "expiry_date")
    val expiryDate: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "location_id")
    val location: Location

) : JpaPersistable(UUID.randomUUID()) {
    @OneToMany(mappedBy = "date",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY)
    val forecast = mutableListOf<Forecast>()
}