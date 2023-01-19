package controller.actions

abstract class MenuAction {
    abstract val command: String
    abstract val description: String?
    abstract fun perform(): Boolean
}