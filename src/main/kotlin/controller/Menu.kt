package controller

import controller.actions.MenuAction
import ui.UI

class Menu(private val prompt: String?, private val defaultAction: MenuAction, vararg actions: MenuAction) {
    private val nonDefaultCommands: Map<String, MenuAction> = actions.associateBy { it.command }
    fun loop() {
        do {
            val input = UI.ask(prompt).trim().lowercase()
        } while (loopIteration(input))
    }

    private fun loopIteration(input: String): Boolean {
        return nonDefaultCommands[input]?.perform() ?: defaultAction.perform()
    }
}