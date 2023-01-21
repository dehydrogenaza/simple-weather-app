package controller

import controller.actions.MenuAction
import ui.UI
import ui.display

class Menu(private val prompt: String?, private val defaultAction: MenuAction, vararg actions: MenuAction) {
    private val menuCommands = actions.associateBy { it.command }
    fun loop() {
        do {
//            prompt?.let { UI.display(it) }
            prompt?.let { it.display() }
        } while (loopIteration())
    }

    private fun loopIteration(): Boolean {
        val input = readln().lowercase()
        return menuCommands[input]?.perform() ?: defaultAction.perform()
    }
}