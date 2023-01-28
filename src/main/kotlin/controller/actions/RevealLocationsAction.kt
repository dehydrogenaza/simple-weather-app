package controller.actions

class RevealLocationsAction : MenuAction() {
    override val command: Regex = arrayOf("/r", "/reveal").toCommand()

    override fun perform(input: String): Boolean {
        TODO("Not yet implemented")
    }
}