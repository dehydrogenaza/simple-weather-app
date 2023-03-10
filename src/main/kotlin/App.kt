import authentication.Credentials
import controller.Menu
import controller.actions.basic.EmptyCommandAction
import controller.actions.basic.EndProgramAction
import controller.actions.basic.InvalidCommandAction
import controller.actions.location.*
import controller.actions.weather.ShowForecastAction
import domain.location.Address
import domain.location.Location
import domain.weather.*
import external_api.HttpClient
import external_api.service.*
import persistence.HibernateStorage
import persistence.Storage
import ui.*
import java.io.IOException
import java.time.LocalDateTime
import java.util.*

fun main() {
    val app = App

    Txt.APP_START_MSG.getFormattedText().display()
    app.initiateLoop()
}

object App {
    init {
        UI.io = ConsoleIO()
        Storage.dao = HibernateStorage() //TODO: Proper error handling, retry + file fallback

//        accuweatherTest()
//        openweatherTest()
        //httpClientTest()

        //addExampleDataToStorage()
//        readExampleDataFromStorage()
    }

    fun initiateLoop() = Menu(
        Txt.MAIN_MENU_PROMPT.getFormattedText(),
        defaultAction = InvalidCommandAction(),
        EmptyCommandAction(),
        ListSavedLocationsAction(),
        AddLocationAction(),
        DeleteLocationsAction(),
        HideLocationsAction(),
        RevealLocationsAction(),
        ShowForecastAction(),
        EndProgramAction()
    ).loop()

    private fun httpClientTest() {
        val client = HttpClient(
            setOf(AccuweatherRetrofitService.create(), OpenweatherRetrofitService.create()),
            Credentials()
        )

        val cities = client.queryCities("Jarocin")
        println(cities.joinToString("\n"))
    }

    private fun accuweatherTest() {
        val accuweatherCityService = AccuweatherRetrofitService.create()
        val apiKeyAccuweather: String = ResourceBundle.getBundle("credentials")
            .getString("api_key_accuweather")

        val citiesApiCall = accuweatherCityService.getCities(apiKeyAccuweather, "Jarocin")
        println("REQUEST URL:")
        println(citiesApiCall.request().url())
        try {
            val response = citiesApiCall.execute()
            val citiesFromQuery = response.body()
            println("CITIES QUERY RESPONSE\n== ACCUWEATHER ==")
            println(citiesFromQuery?.joinToString("\n"))
        } catch (e: IOException) {
            println("Failed to get or parse the API response")
            throw e
        }
    }

    private fun openweatherTest() {
        val openweatherCityService = OpenweatherRetrofitService.create()

        val apiKeyOpenweather: String = ResourceBundle.getBundle("credentials")
            .getString("api_key_openweather")

        val citiesApiCall = openweatherCityService.getCities(apiKeyOpenweather, "Jarocin")
        println("REQUEST URL:")
        println(citiesApiCall.request().url())
        try {
            val response = citiesApiCall.execute()
            val citiesFromQuery = response.body()
            println("CITIES QUERY RESPONSE\n== OPENWEATHER ==")
            println(citiesFromQuery?.joinToString("\n"))
        } catch (e: IOException) {
            println("Failed to get or parse the API response")
            throw e
        }
    }

    private fun readExampleDataFromStorage() {
        val testResults = Storage.readAll(Location::class.java)
        println(testResults)
    }

    private fun addExampleDataToStorage() {
        val testLocation1 = Location("test1", "a", 20.3, 13.5)
        val testLocation2 = Location("test2","b", -10.0, 0.0)
            .apply { address = Address("region2", "country2", "area2", null, this) }
        val testLocation3 = Location("test3","c", 0.0, 1000.0)
            .apply { address = Address("someRegion3", "someCountry3", "someArea3", "someCity3", this) }

        val testDate1 = ForecastDate(LocalDateTime.now(), LocalDateTime.now(), testLocation1)
        val testForecast1provA = Forecast(
            3.0,
            7.0,
            6.0,
            1000.0,
            5.0,
            15.0,
            WindDir.W,
            40,
            15,
            Precipitation.RAIN,
            5.0,
            Provider.ACCUWEATHER,
            testDate1
        )
        val testForecast1provB = Forecast(
            5.0,
            8.0,
            6.0,
            1005.0,
            5.5,
            12.0,
            WindDir.NW,
            45,
            5,
            Precipitation.MIXED,
            1.0,
            Provider.OPENWEATHER,
            testDate1
        )
        testDate1.forecast.add(testForecast1provA)
        testDate1.forecast.add(testForecast1provB)

        val testDate2 = ForecastDate(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(3), testLocation1)
            .apply {
                forecast.add(
                    Forecast(
                        -20.0,
                        -17.0,
                        -30.0,
                        990.0,
                        40.2,
                        85.0,
                        WindDir.S,
                        60,
                        75,
                        Precipitation.SNOW,
                        50.0,
                        Provider.WEATHERSTACK,
                        this
                    )
                )
            }

        Storage.add(testLocation1)
        Storage.add(testLocation2)
        Storage.add(testLocation3)
        Storage.add(testDate1)
        Storage.add(testDate2)
    }
}