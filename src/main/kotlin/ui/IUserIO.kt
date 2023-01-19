package ui

interface IUserIO {
    fun display(msg: String)
    fun ask(msg: String?): String
}