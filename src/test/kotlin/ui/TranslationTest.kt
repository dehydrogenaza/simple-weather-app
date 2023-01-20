package ui

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class TranslationTest {
    @AfterEach
    fun resetLocale() {
        Translation.locale = SupportedLocale.DEFAULT
    }

    @Test
    fun givenStringWithPlaceholders_getFormattedText_returnsValidFormattedString() {
        assertEquals(
            "String <TEST>\n" +
                    "Int -1\n" +
                    "Float 0.500",
            Translation.TEST_PROP_FORMATTED.getFormattedText("<TEST>", -1, 0.5)
        )
    }

    @Test
    fun givenEmptyProp_getFormattedText_returnsEmptyString() {
        assertEquals(
            "",
            Translation.TEST_PROP_EMPTY.getFormattedText()
        )
    }

    @Test
    fun givenLocaleChange_getFormattedText_returnsValidLanguages() {
        assertEquals(
            "This is a simple string nr 1.\n" +
                    "It contains some special/escaped chars:\n" +
                    "#=:\"'`\\()\n" +
                    "It also contains whitespace from here:\n" +
                    "\n" +
                    "\t\n" +
                    "   \n" +
                    "\n" +
                    "...to here.",
            Translation.TEST_PROP_SIMPLE.getFormattedText()
        )
        Translation.locale = SupportedLocale.POLISH
        assertEquals(
            "To jest prosty string nr 1.\n" +
                    "Zawiera polskie znaki: ąęóćłńśźżĄĘÓĆŁŃŚŹŻ",
            Translation.TEST_PROP_SIMPLE.getFormattedText()
        )
        assertEquals(
            "Łańcuch znaków <TEST>\n" +
                    "Liczba całkowita 1000000\n" +
                    "Liczba zmiennoprzecinkowa -0.123",
            Translation.TEST_PROP_FORMATTED.getFormattedText("<TEST>", 1000000, -0.123)
        )
        Translation.locale = SupportedLocale.US
        assertEquals(
            "This is a simple string nr 1.\n" +
                    "It contains some special/escaped chars:\n" +
                    "#=:\"'`\\()\n" +
                    "It also contains whitespace from here:\n" +
                    "\n" +
                    "\t\n" +
                    "   \n" +
                    "\n" +
                    "...to here.",
            Translation.TEST_PROP_SIMPLE.getFormattedText()
        )
    }

    @Test
    fun givenNonexistentProp_getFormattedText_throws() {
        val expectedMsg = "Can't find resource for bundle java.util.PropertyResourceBundle, key test_prop_nonexistent"
        assertThrows(MissingResourceException::class.java) { Translation.TEST_PROP_NONEXISTENT.getFormattedText() }
            .also { assertEquals(expectedMsg, it.message) }
    }

    @Test
    fun givenNotEnoughFormatArguments_getFormattedText_throws() {
        val expectedMsg = "Format specifier '%d'"
        assertThrows(MissingFormatArgumentException::class.java) { Translation.TEST_PROP_FORMATTED.getFormattedText("<TEST>") }
            .also { assertEquals(expectedMsg, it.message) }
    }

    @Test
    fun givenScrambledFormatArguments_getFormattedText_throws() {
        val expectedMsg = "f != java.lang.String"
        assertThrows(IllegalFormatConversionException::class.java) { Translation.TEST_PROP_FORMATTED.getFormattedText(0.5, 1L, "<TEST>") }
            .also { assertEquals(expectedMsg, it.message) }
    }
}