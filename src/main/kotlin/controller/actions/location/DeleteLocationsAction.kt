package controller.actions.location

import controller.actions.IHasArguments
import controller.actions.IMultiChoice
import controller.actions.MenuAction
import domain.location.Location
import persistence.Storage
import ui.Txt
import ui.display

private const val HQL_FIND_LIKE = "FROM Location L WHERE lower(L.description) LIKE lower(:partial)"
class DeleteLocationsAction : MenuAction(), IMultiChoice, IHasArguments {
    override val command: Regex = arrayOf("/d", "/delete").toCommand()

    override fun perform(input: String): Boolean {
        val partialDescription: String = extractArguments(input, Txt.DELETE_COMMAND_PROPER_USAGE_INSTRUCTION)
            ?: return true

        val wasDeleted = Storage.delete(
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

        if (wasDeleted) Txt.DONE_MSG.display(partialDescription) else Txt.DELETED_NOTHING.display()

        return true
    }
}