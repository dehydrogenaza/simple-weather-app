package external_api.service

import external_api.dtos.AccuweatherCityDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import ui.Translation

interface AccuweatherRetrofitService : RetrofitService {
    companion object {
        private const val BASE_URL = "http://dataservice.accuweather.com/"
        private val retrofit: Retrofit = RetrofitService.initRetrofit(BASE_URL)
        fun create(): AccuweatherRetrofitService =
            RetrofitService.createService(retrofit, AccuweatherRetrofitService::class.java)
    }
    override val apiCredentialsId: String
        get() = "api_key_accuweather"

    @GET("/locations/v1/cities/search")
    fun getCities(
        @Query("apikey") apiKey: String,
        @Query("q") cityQuery: String,
        @Query("language") locale: String = currentLocaleString()
    ): Call<List<AccuweatherCityDTO>>

    private fun currentLocaleString(): String = Translation.locale.toString().replace('_', '-').lowercase()
}

//example URL
//http://dataservice.accuweather.com/locations/v1/cities/search?apikey={API}&q={QUERY}&language={LOCALE}