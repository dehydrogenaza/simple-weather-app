package controller.actions

import authentication.Credentials
import domain.Address
import domain.Location
import external_api.HttpClientManager
import external_api.dtos.AccuweatherCityDTO
import external_api.dtos.ApiDTO
import external_api.service.AccuweatherRetrofitService
import persistence.Storage
import ui.Txt
import ui.UI
import ui.ask
import ui.display

private const val HQL_FIND_SIMILAR = "FROM Location L WHERE lower(L.address.city) = lower(:cityName)"

class AddLocationAction : MenuAction() {
    override val command: Regex = arrayOf("/a", "/add").toCommand()

    override fun perform(input: String): Boolean {
        val args: MatchResult? = ARGUMENT_COMMON_REGEX_STRING.toRegex().find(input)

        if (args == null) {
            noArgs()
            return true
        }

        val query = args.value.trim()
        val client = HttpClientManager.get(
            setOf(AccuweatherRetrofitService.create()),
            Credentials()
        )

        val cities = client.queryCities(query)
        cities.forEachIndexed { idx, city ->
            println("${idx + 1}.\n$city")
        }

        val city: ApiDTO = when (cities.size) {
            0 -> {
                Txt.ADD_LOCATION_NOT_FOUND.display(query)
                Txt.ADD_LOCATION_NOTHING_ADDED.display()
                return true
            }

            1 -> cities.first()
            else -> multipleChoice(cities)
        }

        city as AccuweatherCityDTO

        val existing = Storage.read(Location::class.java, HQL_FIND_SIMILAR, mapOf("cityName" to city.cityName))
        if (existing.isNotEmpty()) {
            existing.display()
            if (!shouldAddToExisting(existing.size)) {
                Txt.ADD_LOCATION_NOTHING_ADDED.display()
                return true
            }
        }

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

        // TODO: split to functions

        Txt.LOCATION_ADDED_MSG.display(location.toString())

        return true
    }

    private fun noArgs() = Txt.NO_ARGUMENT_ERROR
        .display(Txt.ADD_COMMAND_PROPER_USAGE_INSTRUCTION.getFormattedText())

    private fun multipleChoice(cities: List<ApiDTO>): ApiDTO {
        val idAsShown = cities.size.let { maxIdAsShown ->
            UI.askUntil(
                1..maxIdAsShown,
                Txt.ADD_MULTIPLE_LOCATIONS_FOUND.getFormattedText(maxIdAsShown),
                Txt.INVALID_ID_ERROR.getFormattedText(maxIdAsShown)
            )
        }
        return cities[idAsShown - 1]
    }

    private fun shouldAddToExisting(numberOfExisting: Int): Boolean {
        return UI.askUntil(
            "[YyNn]".toRegex(),
            Txt.ADD_LOCATION_ALREADY_EXISTS.getFormattedText(numberOfExisting),
            Txt.ADD_LOCATION_ALREADY_EXISTS_ERROR.getFormattedText()
        ).equals("y", ignoreCase = true)
    }
}