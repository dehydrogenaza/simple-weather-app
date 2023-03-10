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
    TEST_PROP_EMPTY("test_prop_empty"),

    APP_START_MSG("app_start_msg"),
    MAIN_MENU_PROMPT("main_menu_prompt"),
    INVALID_COMMAND_ACTION("invalid_command"),
    NO_ARGUMENT_ERROR("no_argument_error"),
    INVALID_ID_ERROR("invalid_id_error"),
    LOCATION_NOT_FOUND("location_not_found"),
    DATE_PARSING_ERROR("date_parsing_error"),

    DONE_MSG("delete_location"),

    ADD_COMMAND_PROPER_USAGE_INSTRUCTION("add_command_proper"),
    ADD_MULTIPLE_LOCATIONS_FOUND("multiple_locations_found"),
    ADD_LOCATION_ALREADY_EXISTS("location_already_exists"),
    ADD_LOCATION_ALREADY_EXISTS_ERROR("location_already_exists_invalid"),
    ADD_LOCATION_NOTHING_ADDED("location_nothing_added"),

    DELETE_COMMAND_PROPER_USAGE_INSTRUCTION("delete_command_proper"),
    DELETE_MATCHES_FOUND("delete_matches_found"),
    DELETED_NOTHING("deleted_nothing"),

    END_PROGRAM_ACTION("end_program"),
    LOCATION_ADDED_MSG("add_location"),
    DISPLAY_LOCATIONS_MSG("display_locations"),

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