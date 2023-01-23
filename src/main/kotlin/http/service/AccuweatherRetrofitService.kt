package http.service

import http.response.AccuweatherCityModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ui.Translation

interface AccuweatherRetrofitService {
    @GET("/locations/v1/cities/search?apikey={apiKey}&q={cityQuery}&language={locale}")
    fun getCities(
        @Path("apiKey") apiKey: String,
        @Path("cityQuery") cityQuery: String,
        @Path("locale") locale: String = localeString()
    ): Call<List<AccuweatherCityModel>>

    private fun localeString(): String = Translation.locale.toString()
}

//example URL
//http://dataservice.accuweather.com/locations/v1/cities/search?apikey={API}&q={QUERY}&language={LOCALE}