package controller.actions

class EmptyCommandAction : MenuAction() {
    override val command: Regex = """\s*""".toRegex()

    override fun perform(): Boolean {
        return true
    }
}