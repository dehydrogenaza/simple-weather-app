package controller.actions

abstract class MenuAction {
    abstract val command: Regex?
    //abstract val description: String?
    abstract fun perform(): Boolean
}