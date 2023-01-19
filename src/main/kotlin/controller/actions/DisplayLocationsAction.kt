package controller.actions

class DisplayLocationsAction : MenuAction() {
    override val identifier = "display"
    override val description = null
    override fun perform(): Boolean {
        println("Display locations action")
        return true
    }
}