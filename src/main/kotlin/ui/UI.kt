package ui

import exceptions.WeatherAppUIException

fun String.display() = UI.display(this) //shorthand for UI.display(msg)
fun Txt.display(vararg formatArgs: Any) = UI.display(this.getFormattedText(*formatArgs))

//fun List<IDisplayableList>.display() = UI.display(this.joinToString("\n"))
fun List<IDisplayableList>.display(showHidden: Boolean = false) =
    UI.display((
            if (showHidden)
                this
            else
                this.filter { !it.isHidden() }
            ).joinToString("\n")
    )
//    UI.display(this.filter { !it.isHidden() }.joinToString("\n"))

fun String.ask() = UI.ask(this) //shorthand for UI.ask(msg)
fun Txt.ask(vararg formatArgs: Any) = UI.ask(this.getFormattedText(*formatArgs))

object UI {

    var io: IUserIO? = null
    fun display(msg: String) = io?.display(msg) ?: failUninitialized()
    fun ask(msg: String?): String = io?.ask(msg) ?: failUninitialized()

    fun askUntil(range: IntRange, firstMsg: String, retryMsg: String): Int {
        return askUntilValid(firstMsg, retryMsg) { msg ->
            ask(msg).toIntOrNull()?.takeIf { it in range }
        }
    }

    fun askUntil(regex: Regex, firstMsg: String, retryMsg: String): String {
        return askUntilValid(firstMsg, retryMsg) { msg ->
            ask(msg).takeIf { it.matches(regex) }
        }
    }

    private fun <T> askUntilValid(
        firstMsg: String,
        retryMsg: String,
        validator: (msg: String) -> T?
    ): T {
        var input: T? = validator(firstMsg)
        while (input == null) {
            input = validator(retryMsg)
        }

        return input
    }

    private fun failUninitialized(): Nothing =
        throw WeatherAppUIException(
            """Input/Output subsystem was not initialized.
            |Initialize by setting UI.io before calling any other UI methods.""".trimMargin()
        )
}