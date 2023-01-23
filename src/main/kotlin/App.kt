import controller.Menu
import controller.actions.*
import domain.Address
import domain.Location
import persistence.HibernateStorage
import persistence.Storage
import ui.*

fun main() {
    App.initiateLoop()
}

object App {
    init {
        UI.io = ConsoleIO()
        Storage.dao = HibernateStorage()

        val testLocation1 = Location("test1", 20.3, 13.5, emptyList())
        val testLocation2 = Location("test3", -10.0, 0.0, emptyList())
            .apply { address = Address("region2", "country2", "area2", null, this) }


        Storage.add(testLocation1)
        Storage.add(testLocation2)
    }

    fun initiateLoop() = Menu(Translation.MAIN_MENU_PROMPT.getFormattedText(), EndProgramAction(), DisplayLocationsAction())
        .loop()
}