package controller.actions

import authentication.Credentials
import domain.Address
import domain.Location
import external_api.HttpClientManager
import external_api.dtos.AccuweatherCityDTO
import external_api.service.AccuweatherRetrofitService
import persistence.Storage
import ui.Txt
import ui.UI
import ui.display

private const val HQL_FIND_SIMILAR = "FROM Location L WHERE lower(L.address.city) = lower(:cityName)"

class AddLocationAction : MenuAction(), IMultiChoice {
    override val command: Regex = arrayOf("/a", "/add").toCommand()

    override fun perform(input: String): Boolean {
        val query: String = extractArguments(input, Txt.ADD_COMMAND_PROPER_USAGE_INSTRUCTION)
            ?: return true

        val cities = findCitiesFromAccuweather(query)
        displayIndexed(cities)

        val city = specifyCityFromList(query, cities) ?: return true

        if (rejectIfAlreadyExists(city.cityName)) return true

        val location: Location = city.let { c ->
            Location(query, c.locationKey, c.geoPosition.latitude, c.geoPosition.longitude)
                .apply {
                    address = Address(
                        c.region.name,
                        c.country.name,
                        "${c.adminArea.type} ${c.adminArea.name}",
                        c.cityName,
                        this
                    )
                }
        }
        Storage.add(location)

        Txt.LOCATION_ADDED_MSG.display(location.toString())

        return true
    }

    private fun findCitiesFromAccuweather(query: String): List<AccuweatherCityDTO> {
        val client = HttpClientManager.get(
            setOf(AccuweatherRetrofitService.create()),
            Credentials()
        )
        return client.queryCities(query).filterIsInstance<AccuweatherCityDTO>()
    }

    private fun specifyCityFromList(query: String, cities: List<AccuweatherCityDTO>): AccuweatherCityDTO? {
        return when (cities.size) {
            0 -> {
                Txt.LOCATION_NOT_FOUND.display(query)
                Txt.ADD_LOCATION_NOTHING_ADDED.display()
                null
            }
            1 -> cities.first()
//            else -> multipleChoice(cities)
            else -> chooseFromList(cities,
                Txt.ADD_MULTIPLE_LOCATIONS_FOUND.getFormattedText(cities.size),
                Txt.INVALID_ID_ERROR.getFormattedText(),
                false
            )
        }
    }

    private fun rejectIfAlreadyExists(cityName: String): Boolean {
        val existing = Storage.read(Location::class.java, HQL_FIND_SIMILAR, mapOf("cityName" to cityName))
        if (existing.isNotEmpty()) {
            existing.display(showHidden = true)
            if (!askToAddToExisting(existing.size)) {
                Txt.ADD_LOCATION_NOTHING_ADDED.display()
                return true
            }
        }
        return false
    }

    private fun askToAddToExisting(numberOfExisting: Int): Boolean {
        return UI.askUntil(
            "[YyNn]".toRegex(),
            Txt.ADD_LOCATION_ALREADY_EXISTS.getFormattedText(numberOfExisting),
            Txt.ADD_LOCATION_ALREADY_EXISTS_ERROR.getFormattedText()
        ).equals("y", ignoreCase = true)
    }
}