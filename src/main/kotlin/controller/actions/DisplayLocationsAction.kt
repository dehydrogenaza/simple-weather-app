package controller.actions

import ui.Translation
import ui.display

class DisplayLocationsAction : MenuAction() {
    override val command = "display"
    override val description = null

    override fun perform(): Boolean {
        Translation.DISPLAY_LOCATIONS_ACTION.getFormattedText()
            .display()
        return true
    }
}