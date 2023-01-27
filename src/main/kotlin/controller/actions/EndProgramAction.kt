package controller.actions

import persistence.Storage
import ui.Txt
import ui.display

class EndProgramAction : MenuAction() {
//    override val command: Regex = """/exit|/e""".toRegex()
    override val command: Regex = arrayOf("/e", "/exit").toCommand()
    //override val description = null

    override fun perform(input: String): Boolean {
        Txt.END_PROGRAM_ACTION.display()
        Storage.close()
        return false
    }
}