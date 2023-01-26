package ui

import domain.Location
import exceptions.WeatherAppUIException

fun String.display() = UI.display(this) //shorthand for UI.display(msg)
fun Txt.display(vararg formatArgs: Any) = UI.display(this.getFormattedText(*formatArgs))
fun List<*>.display() = UI.display(this.joinToString("\n"))
fun String.ask() = UI.ask(this) //shorthand for UI.ask(msg)

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