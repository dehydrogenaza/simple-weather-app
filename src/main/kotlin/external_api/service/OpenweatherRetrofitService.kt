package external_api.service

import external_api.dtos.OpenweatherCityDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenweatherRetrofitService {
    @GET("/geo/1.0/direct")
    fun getCities(
        @Query("appid") apiKey: String,
        @Query("q") cityQuery: String,
        @Query("limit") limit: Int = 5
    ): Call<List<OpenweatherCityDTO>>
}

//EXAMPLE API URL:
//http://api.openweathermap.org/geo/1.0/direct?q={city name},{state code},{country code}&limit={limit}&appid={API key}