package controller.actions

import controller.Menu
import controller.actions.submenu.location.BackCommandAction
import ui.Translation
import ui.display

class ManageLocationsAction : MenuAction() {
    override val command: String = "manage"

    override fun perform(): Boolean {
        Translation.LOCATION_MENU_START_MSG.getFormattedText().display()
        Menu(
            Translation.MAIN_MENU_PROMPT.getFormattedText(),
            InvalidCommandAction(),
            EmptyCommandAction(),
            BackCommandAction()
        ).loop()
        return true
    }
}