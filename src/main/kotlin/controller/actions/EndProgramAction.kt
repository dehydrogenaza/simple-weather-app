package controller.actions

import ui.Translation
import ui.UI

class EndProgramAction : MenuAction() {
    override val command = "end"
    override val description = null

    override fun perform(): Boolean {
        UI.display(Translation.END_PROGRAM_ACTION.getFormattedText())
        return false
    }
}