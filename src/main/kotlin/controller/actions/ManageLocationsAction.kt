package controller.actions

import controller.Menu
import controller.actions.submenu.location.BackCommandAction
import domain.Location
import persistence.Storage
import ui.Txt
import ui.display

class ManageLocationsAction : MenuAction() {
    override val command: String = "list"

    override fun perform(): Boolean {
        Txt.LOCATION_MENU_START_MSG.display()

        Storage.readAll(Location::class.java).display()

        Txt.LOCATION_MANAGER_MSG.display()

        Menu(
            Txt.MAIN_MENU_PROMPT.getFormattedText(),
            InvalidCommandAction(), //default
            EmptyCommandAction(),
            BackCommandAction()
        ).loop()

        return true
    }
}