package controller.actions

class EmptyCommandAction : MenuAction() {
    override val command: String = ""

    override fun perform(): Boolean {
        return true
    }
}