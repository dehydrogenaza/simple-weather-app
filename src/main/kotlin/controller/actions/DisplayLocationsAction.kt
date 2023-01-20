package controller.actions

import ui.TranslationKey
import ui.UI

class DisplayLocationsAction : MenuAction() {
    override val command = "display"
    override val description = null

    override fun perform(): Boolean {
        UI.display(TranslationKey.DISPLAY_LOCATIONS_ACTION)
        return true
    }
}