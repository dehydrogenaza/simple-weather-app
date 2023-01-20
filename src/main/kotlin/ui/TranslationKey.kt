package ui

import java.util.*

enum class TranslationKey(private val propertyKey: String) {
    MAIN_MENU_PROMPT("main_menu_prompt"),
    END_PROGRAM_ACTION("end_program"),
    DISPLAY_LOCATIONS_ACTION("display_locations"),

    ;
    companion object Translator {
        private const val TRANSLATIONS_BUNDLE_NAME = "translation"
        private var txtBundle: ResourceBundle
        var locale: Locale = Locale.US
            set(value) {
                field = value
                txtBundle = getTranslationsBundle()
            }
        init {
            txtBundle = getTranslationsBundle()
        }

        private fun getTranslationsBundle() = ResourceBundle.getBundle(TRANSLATIONS_BUNDLE_NAME, locale)
    }

    fun translate(): String = txtBundle.getString(propertyKey)
}