package http.service

import http.response.AccuweatherCityDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ui.Translation

interface AccuweatherRetrofitService {
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