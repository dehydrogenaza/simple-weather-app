import controller.Menu
import controller.actions.*
import ui.*

fun main() {
    App.initiateLoop()
}

object App {
    init {
        UI.io = ConsoleIO()
    }

    fun initiateLoop() = Menu(Translation.MAIN_MENU_PROMPT.getFormattedText(), EndProgramAction(), DisplayLocationsAction())
        .loop()
}