package controller.actions

import ui.Txt
import ui.display

class EndProgramAction : MenuAction() {
    override val command: Regex = """/exit|/e""".toRegex()
    //override val description = null

    override fun perform(): Boolean {
        Txt.END_PROGRAM_ACTION.display()
        return false
    }
}