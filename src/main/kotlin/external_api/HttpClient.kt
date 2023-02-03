package external_api

import authentication.ICredentialsSource
import authentication.WeatherAppAuthenticationException
import external_api.dto.ApiDTO
import external_api.service.*

class HttpClient(services: Set<RetrofitService>, credentials: ICredentialsSource) {
    val servicesWithKeys: Map<RetrofitService, String> = services.associateWith { service ->
        credentials.data[when (service) {
            is AccuweatherRetrofitService -> AccuweatherRetrofitService.CREDENTIALS_ID
            is OpenweatherRetrofitService -> OpenweatherRetrofitService.CREDENTIALS_ID
        }] ?: failAuthentication(service)
    }

    // TODO: Switch to ASYNC
    // TODO: Exceptions handling for Network Errors (on .execute(), also bad/empty responses in .body())
    fun queryCities(query: String): List<ApiDTO> =
        servicesWithKeys.flatMap {
            when (val service: RetrofitService = it.key) {
                is AccuweatherRetrofitService -> service.getCities(it.value, query)
                is OpenweatherRetrofitService -> service.getCities(it.value, query)
            }.execute().body() ?: emptyList()
        }

    private fun failAuthentication(service: RetrofitService): Nothing =
        throw WeatherAppAuthenticationException(
            "Unable to retrieve the API key for service: " + service::class.simpleName
        )
}