package ui

class ConsoleIO: IUserIO {
    override fun display(msg: String) {
        println(msg)
    }

    override fun ask(msg: String?): String {
        print(msg)
        return readln()
    }
}