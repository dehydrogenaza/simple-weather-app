package external_api.service

import external_api.dtos.OpenweatherCityDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenweatherRetrofitService : RetrofitService {
    companion object {
        private const val BASE_URL = "http://api.openweathermap.org/"
        const val CREDENTIALS_ID: String = "api_key_openweather"

        private val retrofit: Retrofit = RetrofitService.initRetrofit(BASE_URL)

        fun create(): OpenweatherRetrofitService =
            RetrofitService.createService(retrofit, OpenweatherRetrofitService::class.java)
    }

    @GET("/geo/1.0/direct")
    fun getCities(
        @Query("appid") apiKey: String,
        @Query("q") cityQuery: String,
        @Query("limit") limit: Int = 5
    ): Call<List<OpenweatherCityDTO>>
}

//EXAMPLE API URL:
//http://api.openweathermap.org/geo/1.0/direct?q={city name},{state code},{country code}&limit={limit}&appid={API key}