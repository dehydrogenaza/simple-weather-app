package controller.actions

import ui.Translation
import ui.display

class EndProgramAction : MenuAction() {
    override val command = "end"
    //override val description = null

    override fun perform(): Boolean {
        Translation.END_PROGRAM_ACTION.getFormattedText().display()
        return false
    }
}