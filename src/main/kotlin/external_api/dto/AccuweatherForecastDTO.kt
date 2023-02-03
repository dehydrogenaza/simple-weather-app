package external_api.dto

import com.google.gson.annotations.SerializedName

class AccuweatherForecastDTO : ApiDTO {
    @SerializedName("Headline")
    lateinit var headline: HeadlineDto

    @SerializedName("DailyForecasts")
    lateinit var dailyForecasts: Array<DailyForecastDto>

    inner class HeadlineDto(
        @SerializedName("Text")
        val description: String,

        @SerializedName("EffectiveDate")
        val startDate: String,

        @SerializedName("EndDate")
        val endDate: String
    )

    inner class DailyForecastDto {
        @SerializedName("Temperature")
        lateinit var temp: TemperatureDto

        @SerializedName("RealFeelTemperature")
        lateinit var feltTemp: TemperatureDto

        @SerializedName("Day")
        lateinit var day: DayNightDto

        @SerializedName("Night")
        lateinit var night: DayNightDto

        inner class TemperatureDto {
            @SerializedName("Minimum")
            lateinit var min: TemperatureValueDto

            @SerializedName("Maximum")
            lateinit var max: TemperatureValueDto

            inner class TemperatureValueDto(
                @SerializedName("Value")
                val celsius: Double
            )
        }

        inner class DayNightDto(
            @SerializedName("PrecipitationChance")
            val precipitationChance: Double
        ) {
            @SerializedName("LongPhrase")
            lateinit var description: String

            @SerializedName("PrecipitationType")
            lateinit var precipitationType: String

            @SerializedName("Wind")
            lateinit var wind: WindDto

            @SerializedName("WindGust")
            lateinit var windGust: WindDto

            @SerializedName("TotalLiquid")
            lateinit var liquidVolume: LiquidDto

            inner class WindDto {
                @SerializedName("Speed")
                lateinit var speed: WindSpeedDto

                @SerializedName("Direction")
                lateinit var direction: WindDirectionDto
                inner class WindSpeedDto(
                    @SerializedName("Value")
                    val kmh: Double
                )

                inner class WindDirectionDto(
                    @SerializedName("English")
                    val dir: String
                )
            }

            inner class LiquidDto(
                @SerializedName("Value")
                val mm: Double
            )
        }
    }
}