package controller.actions

import ui.UI

class EndProgramAction : MenuAction() {
    override val identifier = "end"
    override val description = null
    override fun perform(): Boolean {
        UI.display("End program action")
        return false
    }
}