package ui

import exceptions.WeatherAppUIException

object UI {
    var io: IUserIO? = null
    fun display(msg: Any) = io?.display(msg.toString()) ?: failUninitialized()
    fun ask(msg: String?) = io?.ask(msg) ?: failUninitialized()

    private fun failUninitialized(): Nothing =
        throw WeatherAppUIException("""Input/Output subsystem was not initialized.
            |Initialize by setting UI.io before calling any other UI methods.""".trimMargin())
}