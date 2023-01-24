package http.response

import com.google.gson.annotations.SerializedName

class AccuweatherCityDTO(
    @SerializedName("Key")
    private val locationKey: String,
    @SerializedName("LocalizedName")
    private val cityName: String
) {
    @SerializedName("Region")
    private lateinit var region: AccuweatherRegionDto
    @SerializedName("Country")
    private lateinit var country: AccuweatherCountryDto
    @SerializedName("AdministrativeArea")
    private lateinit var adminArea: AccuweatherAdminAreaDto
    @SerializedName("GeoPosition")
    private lateinit var geoPosition: AccuweatherGeoPositionDto

    override fun toString(): String {
        return "CITY ($locationKey): $cityName" +
                "\n\t${region.name}, ${country.name}, ${adminArea.type} ${adminArea.name}" +
                "\n\tLatitude = ${geoPosition.latitude} Longitude = ${geoPosition.longitude})"
    }

    private class AccuweatherCountryDto(
        @SerializedName("LocalizedName")
        val name: String
    )

    private class AccuweatherRegionDto(
        @SerializedName("LocalizedName")
        val name: String
    )

    private class AccuweatherAdminAreaDto(
        @SerializedName("LocalizedType")
        val type: String,
        @SerializedName("LocalizedName")
        val name: String
    )

    private class AccuweatherGeoPositionDto(
        @SerializedName("Latitude")
        val latitude: Double,
        @SerializedName("Longitude")
        val longitude: Double
    )
}

//private class AccuweatherCountryDto(
//    @SerializedName("LocalizedName")
//    val name: String
//)
//
//private class AccuweatherRegionDto(
//    @SerializedName("LocalizedName")
//    val name: String
//)
//
//private class AccuweatherAdminAreaDto(
//    @SerializedName("LocalizedType")
//    val type: String,
//    @SerializedName("LocalizedName")
//    val name: String
//)
//
//private class AccuweatherGeoPositionDto(
//    @SerializedName("Latitude")
//    val latitude: Double,
//    @SerializedName("Longitude")
//    val longitude: Double
//)

//ACTUAL VERSION:
//locationKey
//latitude
//longitude
//region
//country
//adminArea
//city