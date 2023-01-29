package controller.actions

import domain.Location
import persistence.Storage
import ui.Txt
import ui.display

private const val HQL_FIND_LIKE = "FROM Location L WHERE lower(L.description) LIKE lower(:partial)"
class DeleteLocationsAction : MenuAction(), IMultiChoice {
    override val command: Regex = arrayOf("/d", "/delete").toCommand()

    override fun perform(input: String): Boolean {
        val partialDescription: String = extractArguments(input, Txt.DELETE_COMMAND_PROPER_USAGE_INSTRUCTION)
            ?: return true

        val done = Storage.delete(
            Location::class.java,
            HQL_FIND_LIKE,
            mapOf("partial" to "%$partialDescription%")
        ) {
            displayIndexed(it)
            chooseFromList(
                it,
                Txt.DELETE_MATCHES_FOUND.getFormattedText(it.size),
                Txt.INVALID_ID_ERROR.getFormattedText(),
                true
            )
        }

        if (done) Txt.DONE_MSG.display(partialDescription)

        return true
    }
}