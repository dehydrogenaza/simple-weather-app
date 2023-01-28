package external_api

import authentication.ICredentialsSource
import external_api.service.AccuweatherRetrofitService
import external_api.service.OpenweatherRetrofitService
import external_api.service.RetrofitService

object HttpClientManager {
    private val clients: MutableSet<HttpClient> = mutableSetOf()

    fun get(services: Set<RetrofitService>, credentials: ICredentialsSource): HttpClient =
        clients.firstOrNull { services.contentHash == it.servicesWithKeys.keys.contentHash }
            ?: registerClient(services, credentials)


    private fun registerClient(services: Set<RetrofitService>, credentials: ICredentialsSource) =
        HttpClient(services, credentials)
            .also { clients += it }

    private val Set<RetrofitService>.contentHash: Int
        get() = this.fold(0) { acc, service ->
            acc + when (service) {
                is AccuweatherRetrofitService -> 1
                is OpenweatherRetrofitService -> 10
            }
        }

}