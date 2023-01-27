package controller.actions.submenu.location

import controller.actions.MenuAction
import ui.Txt
import ui.display

class BackFromLocationsAction : MenuAction() {
    override val command: Regex = """/back|/b""".toRegex()

    override fun perform(input: String): Boolean {
        Txt.LOCATION_MENU_BACK.display()
        return false
    }
}