package controller.actions

import ui.TranslationKey
import ui.UI

class EndProgramAction : MenuAction() {
    override val command = "end"
    override val description = null

    override fun perform(): Boolean {
        UI.display(TranslationKey.END_PROGRAM_ACTION)
        return false
    }
}