package controller.actions

class HideLocationsAction : MenuAction() {
    override val command: Regex = arrayOf("/h", "/hide").toCommand()

    override fun perform(input: String): Boolean {
        TODO("Not yet implemented")
    }
}