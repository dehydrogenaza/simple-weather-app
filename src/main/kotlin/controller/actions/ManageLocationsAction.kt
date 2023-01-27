package controller.actions

import controller.Menu
import controller.actions.submenu.location.*
import ui.Txt
import ui.display

class ManageLocationsAction : MenuAction() {
    override val command: Regex = arrayOf("/l", "/list", "/locations").toCommand()
//    override val command: Regex = """/list|/locations|/l""".toRegex()

    override fun perform(input: String): Boolean {
        Txt.LOCATION_MENU_START_MSG.display()

        //TODO: This is the actual logic, commented due to ease of development
        //Storage.readAll(Location::class.java).display()

        Txt.LOCATION_MANAGER_MSG.display()

        val deleteLocationAction = object : MenuAction() {
            override val command: Regex = """(/d|/delete) +\S.*""".toRegex()
            override fun perform(input: String): Boolean {
                TODO("Not yet implemented")
            }
        }

        val hideLocationAction = object : MenuAction() {
            override val command: Regex = """(/h|/hide) +\S.*""".toRegex()
            override fun perform(input: String): Boolean {
                TODO("Not yet implemented")
            }
        }

        val showLocationAction = object : MenuAction() {
            override val command: Regex = """(/s|/show) +\S.*""".toRegex()
            override fun perform(input: String): Boolean {
                TODO("Not yet implemented")
            }
        }

        Menu(
            Txt.MAIN_MENU_PROMPT.getFormattedText(),
            defaultAction = AddLocationAction(),
            EmptyCommandAction(),
            BackFromLocationsAction(),
            deleteLocationAction,
            hideLocationAction,
            showLocationAction,
        ).loop()

        return true
    }
}