package external_api.service

import external_api.dto.AccuweatherCityDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import ui.Txt

interface AccuweatherRetrofitService : RetrofitService {
    companion object {
        private const val BASE_URL = "http://dataservice.accuweather.com/"
        const val CREDENTIALS_ID: String = "api_key_accuweather"

        private val retrofit: Retrofit = RetrofitService.initRetrofit(BASE_URL)

        fun create(): AccuweatherRetrofitService =
            RetrofitService.createService(retrofit, AccuweatherRetrofitService::class.java)
    }

    @GET("/locations/v1/cities/search")
    fun getCities(
        @Query("apikey") apiKey: String,
        @Query("q") cityQuery: String,
        @Query("language") locale: String = currentLocaleString()
    ): Call<List<AccuweatherCityDTO>>

    private fun currentLocaleString(): String = Txt.locale.toString().replace('_', '-').lowercase()
}

//example URL
//http://dataservice.accuweather.com/locations/v1/cities/search?apikey={API}&q={QUERY}&language={LOCALE}