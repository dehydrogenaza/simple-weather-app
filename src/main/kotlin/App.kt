import controller.Menu
import controller.actions.*
import ui.*

fun main() {
    App.initiateLoop()
}

object App {
    private val mainMenu = Menu(TranslationKey.MAIN_MENU_PROMPT, EndProgramAction(), DisplayLocationsAction())
    init {
        UI.io = ConsoleIO()
    }

    fun initiateLoop() = mainMenu.loop()
}