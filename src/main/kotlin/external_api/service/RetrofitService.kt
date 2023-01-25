package external_api.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

sealed interface RetrofitService {
    companion object {
        protected val gsonConverter: GsonConverterFactory = GsonConverterFactory.create()
        fun initRetrofit(baseUrl: String): Retrofit =
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverter)
                .build()


        fun <T : RetrofitService> createService(retrofit: Retrofit, serviceClass: Class<T>): T =
            retrofit.create(serviceClass)
    }
}