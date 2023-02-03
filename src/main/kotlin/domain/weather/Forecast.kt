package domain.weather

import domain.DbTableNames
import domain.JpaPersistable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = DbTableNames.FORECAST)
final class Forecast(
    @Column(name = "min_temp")
    val minTemp: Double,

    @Column(name = "max_temp")
    val maxTemp: Double,

    @Column(name = "felt_temp")
    val feltTemp: Double,

    @Column(name = "pressure")
    val pressure: Double,

    @Column(name = "wind_avg_speed")
    val windAvgSpeed: Double,

    @Column(name = "wind_gust_speed")
    val windGustSpeed: Double,

    @Column(name = "wind_direction")
    @Enumerated(EnumType.STRING)
    val windDirection: WindDir,

    @Column(name = "humidity")
    val humidity: Int,

    @Column(name = "precipitation_chance")
    val precipitationChance: Int,

    @Column(name = "precipitation_type")
    @Enumerated(EnumType.STRING)
    val precipitationType: Precipitation,

    @Column(name = "precipitation_volume")
    val precipitationVolume: Double,

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    val provider: Provider,

    @ManyToOne
    @JoinColumn(name = "date_id")
    val date: ForecastDate

) : JpaPersistable(UUID.randomUUID())