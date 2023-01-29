package controller.actions

import domain.Location
import persistence.Storage
import ui.Txt
import ui.display

class ListSavedLocationsAction : MenuAction() {
    override val command: Regex = arrayOf("/l", "/list", "/locations").toCommand()

    override fun perform(input: String): Boolean {
        Txt.DISPLAY_LOCATIONS_MSG.display()

        Storage.readAll(Location::class.java).display(showHidden = true)

        return true
    }

}