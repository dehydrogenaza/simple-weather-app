package ui

import exceptions.WeatherAppUIException

object UI {

    var io: IUserIO? = null
    fun display(msgKey: TranslationKey) = io?.display(msgKey.translate()) ?: failUninitialized()
    fun ask(msgKey: TranslationKey?) = io?.ask(msgKey?.translate()) ?: failUninitialized()
    private fun failUninitialized(): Nothing =
        throw WeatherAppUIException(
            """Input/Output subsystem was not initialized.
            |Initialize by setting UI.io before calling any other UI methods.""".trimMargin()
        )
}