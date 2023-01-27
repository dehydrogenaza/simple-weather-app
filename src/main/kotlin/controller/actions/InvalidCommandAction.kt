package controller.actions

import ui.Txt
import ui.display

class InvalidCommandAction : MenuAction() {
    override val command: Regex? = null
    //override val description: String?

    override fun perform(): Boolean {
        Txt.INVALID_COMMAND_ACTION.display()
        return true
    }
}