package controller.actions.submenu.location

import controller.actions.MenuAction
import ui.Txt
import ui.display

class AddLocationAction : MenuAction() {
    override val command: Regex? = null

    override fun perform(input: String): Boolean {
        println("PLACEHOLDER (should read from API, schedule to DB then upload)")
        Txt.LOCATION_ADDED_MSG.display(input)
        return true
    }
}