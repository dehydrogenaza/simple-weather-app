package controller.actions

class DeleteLocationsAction : MenuAction() {
    override val command: Regex = arrayOf("/d", "/delete").toCommand()

    override fun perform(input: String): Boolean {
        TODO("Not yet implemented")
    }
}