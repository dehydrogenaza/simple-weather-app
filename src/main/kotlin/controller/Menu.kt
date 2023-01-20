package controller

import controller.actions.MenuAction
import ui.TranslationKey
import ui.UI

class Menu(private val prompt: TranslationKey?, private val defaultAction: MenuAction, vararg actions: MenuAction) {
    private val menuCommands = actions.associateBy { it.command }
    fun loop() {
        do {
            prompt?.let { UI.display(it) }
        } while (loopIteration())
    }

    private fun loopIteration(): Boolean {
        val input = readln().lowercase()
        return menuCommands[input]?.perform() ?: defaultAction.perform()
    }
}