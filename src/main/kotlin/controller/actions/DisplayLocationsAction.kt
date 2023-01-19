package controller.actions

import ui.UI

class DisplayLocationsAction : MenuAction() {
    override val command = "display"
    override val description = null

    private val msgKey = "display_locations"
    override fun perform(): Boolean {
        UI.display(msgKey)
//        UI.TranslationSource.locale = Locale("pl", "pl")
//        UI.display(msgKey)
        return true
    }
}