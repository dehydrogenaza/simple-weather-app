package controller.actions

import ui.UI

class EndProgramAction : MenuAction() {
    override val command = "end"
    override val description = null

    private val msgKey = "end_program"
    override fun perform(): Boolean {
        UI.display(msgKey)
        return false
    }
}