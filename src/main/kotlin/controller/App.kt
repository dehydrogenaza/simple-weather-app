package controller

import controller.actions.DisplayLocationsAction
import controller.actions.EndProgramAction
import ui.ConsoleIO
import ui.UI

object App {
    private val mainMenu = Menu(">", EndProgramAction(), DisplayLocationsAction())
    init {
        UI.io = ConsoleIO()
    }

    fun initiateLoop() = mainMenu.loop()
}