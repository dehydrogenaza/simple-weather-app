package external_api

import external_api.service.*
import java.util.ResourceBundle

class HttpClient(services: List<RetrofitService>) {
    //private val services: List<RetrofitService> = services

    private val servicesWithKeys: Map<RetrofitService, String> =
        services.associateWith { service ->
            ResourceBundle.getBundle("credentials").getString(service.apiCredentialsId)
        }

    init {
        for (service in services) {
            val apiCredentialsId = service.apiCredentialsId
            val apiKey = ResourceBundle.getBundle("credentials").getString(apiCredentialsId)
        }
    }

    fun getCities(query: String) {

    }
}