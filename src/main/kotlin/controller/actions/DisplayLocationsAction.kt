package controller.actions

import ui.UI

class DisplayLocationsAction : MenuAction() {
    override val identifier = "display"
    override val description = null
    override fun perform(): Boolean {
        UI.display("Display locations action")
        return true
    }
}