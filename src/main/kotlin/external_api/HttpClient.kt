package external_api

import external_api.service.*
import java.util.ResourceBundle

class HttpClient(services: List<RetrofitService>) {

    private val servicesWithKeys: Map<RetrofitService, String> = services.associateWith { service ->
        ResourceBundle.getBundle("credentials").getString(when (service) {
            is AccuweatherRetrofitService -> AccuweatherRetrofitService.CREDENTIALS_ID
            is OpenweatherRetrofitService -> OpenweatherRetrofitService.CREDENTIALS_ID
        })
    }

    fun queryCities(query: String) =
        servicesWithKeys.flatMap {
            when (val service: RetrofitService = it.key) {
                is AccuweatherRetrofitService -> service.getCities(it.value, query)
                is OpenweatherRetrofitService -> service.getCities(it.value, query)
            }.execute().body() ?: emptyList()
        }

}