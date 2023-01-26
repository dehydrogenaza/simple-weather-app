package authentication

interface ICredentialsSource {
    val data: Map<String, String>
}