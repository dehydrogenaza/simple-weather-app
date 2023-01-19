package controller.actions

abstract class MenuAction {
    abstract val identifier: String
    abstract val description: String?
    abstract fun perform(): Boolean
}