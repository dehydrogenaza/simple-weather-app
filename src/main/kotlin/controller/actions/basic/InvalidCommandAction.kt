package controller.actions.basic

import controller.actions.MenuAction
import ui.Txt
import ui.display

class InvalidCommandAction : MenuAction() {
    override val command: Regex? = null
    //override val description: String?

    override fun perform(input: String): Boolean {
        Txt.INVALID_COMMAND_ACTION.display()
        return true
    }
}