package controller.actions

class EndProgramAction : MenuAction() {
    override val identifier = "end"
    override val description = null
    override fun perform(): Boolean {
        println("End program action")
        return false
    }
}