package controller

import controller.actions.MenuAction

class Menu(private val prompt: String?, private val defaultAction: MenuAction, vararg actions: MenuAction) {
    private val menuCommands = actions.associateBy { it.command }
    fun loop() {
        do {
            println(prompt)
        } while (loopIteration())
    }

    private fun loopIteration(): Boolean {
        val input = readln().lowercase()
        return menuCommands[input]?.perform() ?: defaultAction.perform()
    }
}