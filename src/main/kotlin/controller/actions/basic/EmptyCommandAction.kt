package controller.actions.basic

import controller.actions.MenuAction

class EmptyCommandAction : MenuAction() {
    override val command: Regex = """\s*""".toRegex()

    override fun perform(input: String): Boolean {
        return true
    }
}