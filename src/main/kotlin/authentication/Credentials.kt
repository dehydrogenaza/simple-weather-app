package authentication

import java.util.ResourceBundle

class Credentials(bundleFile: String = "credentials") : ICredentialsSource {
    private val dataBundle: ResourceBundle = ResourceBundle.getBundle(bundleFile)
    private val dataKeys: Array<String> = arrayOf(
        "db_url",
        "db_username",
        "db_password",
        "api_key_accuweather",
        "api_key_openweather"
    )

    override val data: Map<String, String> = dataKeys.associateWith { dataBundle.getString(it) }
}