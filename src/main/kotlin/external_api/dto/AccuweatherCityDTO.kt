package external_api.dto

import com.google.gson.annotations.SerializedName

class AccuweatherCityDTO(
    @SerializedName("Key")
    val locationKey: String,
    @SerializedName("LocalizedName")
    val cityName: String
) : ApiDTO {
    @SerializedName("Region")
    lateinit var region: AccuweatherRegionDto
    @SerializedName("Country")
    lateinit var country: AccuweatherCountryDto
    @SerializedName("AdministrativeArea")
    lateinit var adminArea: AccuweatherAdminAreaDto
    @SerializedName("GeoPosition")
    lateinit var geoPosition: AccuweatherGeoPositionDto

    override fun toString(): String {
        return "CITY (AccuWeather) - ($locationKey): $cityName" +
                "\n\t${region.name}, ${country.name}, ${adminArea.type} ${adminArea.name}" +
                "\n\tLatitude = ${geoPosition.latitude} Longitude = ${geoPosition.longitude})"
    }

    inner class AccuweatherCountryDto(
        @SerializedName("LocalizedName")
        val name: String
    )

    inner class AccuweatherRegionDto(
        @SerializedName("LocalizedName")
        val name: String
    )

    inner class AccuweatherAdminAreaDto(
        @SerializedName("LocalizedType")
        val type: String,
        @SerializedName("LocalizedName")
        val name: String
    )

    inner class AccuweatherGeoPositionDto(
        @SerializedName("Latitude")
        val latitude: Double,
        @SerializedName("Longitude")
        val longitude: Double
    )
}