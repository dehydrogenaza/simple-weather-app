package controller.actions.weather

import authentication.Credentials
import controller.actions.IHasArguments
import controller.actions.MenuAction
import external_api.HttpClientManager
import external_api.service.AccuweatherRetrofitService
import kotlinx.datetime.*
import ui.Txt
import ui.display

class ShowForecastAction : MenuAction(), IHasArguments {
    override val command: Regex = arrayOf("/w", "/weather").toCommand()

    override fun perform(input: String): Boolean {
        val arg: String? = extractArguments(input, mandatory = false)
        val date: LocalDate = if (arg != null) {
            try {
                arg.toLocalDate()
            } catch (e: IllegalArgumentException) {
                Txt.DATE_PARSING_ERROR.display(arg)
                return true
            }
        } else {
            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        }

        val client = HttpClientManager.get(
            setOf(AccuweatherRetrofitService.create()),
            Credentials()
        )

        return true
    }
}