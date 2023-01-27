package controller.actions

class EmptyCommandAction : MenuAction() {
    override val command: Regex = """\s*""".toRegex()

    override fun perform(input: String): Boolean {
        return true
    }
}