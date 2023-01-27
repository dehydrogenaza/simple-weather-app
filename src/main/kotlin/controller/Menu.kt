package controller

import controller.actions.MenuAction
import ui.UI

class Menu(private val prompt: String?, private val defaultAction: MenuAction, vararg actions: MenuAction) {
    private val nonDefaultCommands: Map<MenuAction, Regex?> = actions.associateWith { it.command }
    fun loop() {
        do {
            val input = UI.ask(prompt).trim().lowercase()
        } while (loopIteration(input))
    }

    private fun loopIteration(input: String): Boolean {
        nonDefaultCommands.forEach { (action, command) ->
            command?.let { if (input.matches(it)) return action.perform() }
        }
        return defaultAction.perform()
        //return nonDefaultCommands[input]?.perform() ?: defaultAction.perform()
    }
}