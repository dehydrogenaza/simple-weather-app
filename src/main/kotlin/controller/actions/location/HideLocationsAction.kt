package controller.actions.location

import controller.actions.MenuAction

class HideLocationsAction : MenuAction() {
    override val command: Regex = arrayOf("/h", "/hide").toCommand()

    override fun perform(input: String): Boolean {
        TODO("Not yet implemented")
    }
}