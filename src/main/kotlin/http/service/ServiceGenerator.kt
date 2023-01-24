package http.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class ServiceGenerator(baseUrl: String) {
    companion object {
        val gsonConverter: GsonConverterFactory = GsonConverterFactory.create()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(gsonConverter)
        .build()

    fun <T> createService(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}

object AccuweatherServiceGenerator : ServiceGenerator("http://dataservice.accuweather.com/")