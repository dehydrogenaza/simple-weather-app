import controller.Menu
import controller.actions.*
import domain.Address
import domain.ForecastDate
import domain.Location
import domain.weather.Forecast
import domain.weather.Precipitation
import domain.weather.Provider
import domain.weather.WindDir
import persistence.HibernateStorage
import persistence.Storage
import ui.*
import java.time.LocalDateTime

fun main() {
    App.initiateLoop()
}

object App {
    init {
        UI.io = ConsoleIO()
        Storage.dao = HibernateStorage()

        addExampleDataToStorage()
        val testResults = Storage.readAll(Location::class.java)
        println(testResults)
    }

    fun initiateLoop() = Menu(Translation.MAIN_MENU_PROMPT.getFormattedText(), EndProgramAction(), DisplayLocationsAction())
        .loop()

    private fun readExampleDataFromStorage() {

    }

    private fun addExampleDataToStorage() {
        val testLocation1 = Location("test1", 20.3, 13.5)
        val testLocation2 = Location("test2", -10.0, 0.0)
            .apply { address = Address("region2", "country2", "area2", null, this) }
        val testLocation3 = Location("test3", 0.0, 1000.0)
            .apply { address = Address("someRegion3", "someCountry3", "someArea3", "someCity3", this) }

        val testDate1 = ForecastDate(LocalDateTime.now(), LocalDateTime.now(), testLocation1)
        val testForecast1provA = Forecast(3.0, 7.0, 6.0, 1000.0, 5.0, 15.0, WindDir.W, 40, 15, Precipitation.RAIN, 5.0, Provider.ACCUWEATHER, testDate1)
        val testForecast1provB = Forecast(5.0, 8.0, 6.0, 1005.0, 5.5, 12.0, WindDir.NW, 45, 5, Precipitation.MIXED, 1.0, Provider.OPENWEATHER, testDate1)
        testDate1.forecast.add(testForecast1provA)
        testDate1.forecast.add(testForecast1provB)

        val testDate2 = ForecastDate(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(3), testLocation1)
            .apply { forecast.add(Forecast(-20.0, -17.0, -30.0, 990.0, 40.2, 85.0, WindDir.S, 60, 75, Precipitation.SNOW, 50.0, Provider.WEATHERSTACK, this)) }

        Storage.add(testLocation1)
        Storage.add(testLocation2)
        Storage.add(testLocation3)
        Storage.add(testDate1)
        Storage.add(testDate2)
    }
}