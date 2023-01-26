package controller.actions

import ui.Txt
import ui.display

class InvalidCommandAction : MenuAction() {
    override val command: String = ""
    //override val description: String?

    override fun perform(): Boolean {
        Txt.INVALID_COMMAND_ACTION.display()
        return true
    }
}