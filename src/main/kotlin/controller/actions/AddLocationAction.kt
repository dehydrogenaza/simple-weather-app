package controller.actions

import authentication.Credentials
import external_api.HttpClientManager
import external_api.dtos.ApiDTO
import external_api.service.AccuweatherRetrofitService
import ui.Txt
import ui.UI
import ui.display

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

        val city: ApiDTO? = when (cities.size) {
            0 -> {
                Txt.ADD_LOCATION_NOT_FOUND.display(query)
                null
            }
            1 -> cities.first()
            else -> multipleChoice(cities)
        }

        Txt.LOCATION_ADDED_MSG.display(city.toString())

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
}