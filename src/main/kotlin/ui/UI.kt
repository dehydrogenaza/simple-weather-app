package ui

import exceptions.WeatherAppUIException

object UI {

    var io: IUserIO? = null
    fun display(msg: String) = io?.display(msg) ?: failUninitialized()
    fun ask(msg: String?): String = io?.ask(msg) ?: failUninitialized()
    private fun failUninitialized(): Nothing =
        throw WeatherAppUIException(
            """Input/Output subsystem was not initialized.
            |Initialize by setting UI.io before calling any other UI methods.""".trimMargin()
        )
}