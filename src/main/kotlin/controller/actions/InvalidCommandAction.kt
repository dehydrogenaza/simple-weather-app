package controller.actions

import ui.Translation
import ui.display

class InvalidCommandAction : MenuAction() {
    override val command: String = ""
    //override val description: String?

    override fun perform(): Boolean {
        Translation.INVALID_COMMAND_ACTION.getFormattedText().display()
        return true
    }
}