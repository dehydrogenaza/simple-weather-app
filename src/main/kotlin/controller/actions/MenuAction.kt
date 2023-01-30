package controller.actions

import ui.Txt
import ui.UI
import ui.display
import kotlin.text.StringBuilder

const val ARGUMENT_COMMON_REGEX_STRING = " +\\S.*"
private const val AFTER_PARAMETER_COMMON_REGEX_STRING = "( .+)*"

abstract class MenuAction {
    abstract val command: Regex?

    //abstract val description: String?
    abstract fun perform(input: String): Boolean

    protected fun Array<String>.toCommand(): Regex {
        val parameterBase: String = when (this.size) {
            0 -> ""
            1 -> this.first()
            else -> {
                val sb: StringBuilder = StringBuilder("(")
                this.forEach { alias ->
                    sb.append("$alias|")
                }
                sb.toString().dropLast(1) + ")"
            }
        }

        return if (parameterBase.isEmpty()) {
            ""
        } else {
            parameterBase + AFTER_PARAMETER_COMMON_REGEX_STRING
        }.toRegex()
    }
}

interface IHasArguments {
    fun extractArguments(input: String, properInstruction: Txt? = null, mandatory: Boolean = true): String? {
        val args: MatchResult? = ARGUMENT_COMMON_REGEX_STRING.toRegex().find(input)
        return if (args == null) {
            if (properInstruction != null && mandatory) {
                noArgs(properInstruction.getFormattedText())
            }
            null
        } else {
            args.value.trim()
        }
    }

    private fun noArgs(properInstruction: String) = Txt.NO_ARGUMENT_ERROR
        .display(properInstruction)
}

interface IMultiChoice {
    fun displayIndexed(list: List<Any>) = list.forEachIndexed { idx, element ->
        println("${idx + 1}.\n$element")
    }

    fun <T> chooseFromList(choices: List<T>, firstMsg: String, retryMsg: String, allowNone: Boolean = false): T? {
        val maxIdAsShown = choices.size
        val id = UI.askUntil(
            IntRange(if (allowNone) 0 else 1, maxIdAsShown),
            firstMsg,
            retryMsg
        )
        return if (id == 0) null else choices[id - 1]
    }
}