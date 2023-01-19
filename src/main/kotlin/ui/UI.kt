package ui

import exceptions.WeatherAppUIException
import java.util.*

object UI {
    var io: IUserIO? = null
    private var translation = TranslationSource
    fun display(msgKey: Any) = io?.display(translation.txt(msgKey)) ?: failUninitialized()
    fun ask(msgKey: String?) = io?.ask(translation.txt(msgKey)) ?: failUninitialized()

    private fun failUninitialized(): Nothing =
        throw WeatherAppUIException("""Input/Output subsystem was not initialized.
            |Initialize by setting UI.io before calling any other UI methods.""".trimMargin())

    object TranslationSource {
        private const val TRANSLATIONS_BUNDLE_NAME = "translation"

        private var txtBundle: ResourceBundle
        var locale = Locale.US
            set(value) {
                field = value
                txtBundle = ResourceBundle.getBundle(TRANSLATIONS_BUNDLE_NAME, locale)
            }
        init {
            txtBundle = ResourceBundle.getBundle(TRANSLATIONS_BUNDLE_NAME, locale)
        }

        fun txt(key: Any?): String = txtBundle.getString(key.toString())
    }
}