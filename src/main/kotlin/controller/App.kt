package controller

import controller.actions.DisplayLocationsAction
import controller.actions.EndProgramAction

object App {
    private val mainMenu = Menu(">", EndProgramAction(), DisplayLocationsAction())

    fun initiateLoop() = mainMenu.loop()
}