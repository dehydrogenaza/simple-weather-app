package ui

import java.util.*

enum class SupportedLocale(val actualValue: Locale) {
    DEFAULT(Locale.US),
    US(Locale.US),
    POLISH(Locale("pl", "PL"));

    override fun toString(): String = actualValue.toString()
}

enum class Txt(private val propertyKey: String) {
    TEST_PROP_SIMPLE("test_prop_simple"),
    TEST_PROP_FORMATTED("test_prop_formatted"),
    TEST_PROP_NONEXISTENT("test_prop_nonexistent"),

    APP_START_MSG("app_start_msg"),
    TEST_PROP_EMPTY("test_prop_empty"),
    MAIN_MENU_PROMPT("main_menu_prompt"),
    END_PROGRAM_ACTION("end_program"),
    INVALID_COMMAND_ACTION("invalid_command"),
    LOCATION_MENU_START_MSG("location_menu_start_msg"),
    LOCATION_MENU_BACK("app_start_msg"),
    LOCATION_MANAGER_MSG("location_manager_msg"),
    LOCATION_ADDED_MSG("add_location"),

    ;

    companion object Translator {
        private const val TRANSLATIONS_BUNDLE_NAME = "translation"

        private var txtBundle: ResourceBundle
        var locale: SupportedLocale = SupportedLocale.DEFAULT
            set(value) {
                field = value
                txtBundle = getTranslationsBundle()
            }
        init {
            txtBundle = getTranslationsBundle()
        }

        private fun getTranslationsBundle() = ResourceBundle.getBundle(TRANSLATIONS_BUNDLE_NAME, locale.actualValue)
    }

    fun getFormattedText(vararg formatArgs: Any): String =
        txtBundle.getString(propertyKey).format(args = formatArgs)
}