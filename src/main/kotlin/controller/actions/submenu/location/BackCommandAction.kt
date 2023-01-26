package controller.actions.submenu.location

import controller.actions.MenuAction
import ui.Translation
import ui.display

class BackCommandAction : MenuAction() {
    override val command: String = "back"

    override fun perform(): Boolean {
        Translation.LOCATION_MENU_BACK.getFormattedText().display()
        return false
    }
}