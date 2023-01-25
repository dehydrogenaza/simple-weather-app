package external_api.dtos

import com.google.gson.annotations.SerializedName

class OpenweatherCityDTO(
    @SerializedName("name")
    private val cityName: String,
    @SerializedName("lat")
    private val latitude: Double,
    @SerializedName("lon")
    private val longitude: Double,
    @SerializedName("country")
    private val country: String,
    @SerializedName("state")
    private val state: String?
) {
    override fun toString(): String {
        return "CITY (OpenWeather) - $cityName" +
                "\n\t$country, ${state ?: ""}" +
                "\n\tLatitude = $latitude Longitude = $longitude"
    }
}