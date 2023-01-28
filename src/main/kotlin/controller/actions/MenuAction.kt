package controller.actions

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
