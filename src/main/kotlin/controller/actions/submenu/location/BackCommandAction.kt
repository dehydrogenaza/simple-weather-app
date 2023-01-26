package controller.actions.submenu.location

import controller.actions.MenuAction
import ui.Txt
import ui.display

class BackCommandAction : MenuAction() {
    override val command: String = "back"

    override fun perform(): Boolean {
        Txt.LOCATION_MENU_BACK.display()
        return false
    }
}