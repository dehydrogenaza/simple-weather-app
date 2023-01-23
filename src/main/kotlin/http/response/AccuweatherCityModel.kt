package http.response

class AccuweatherCityModel(
    val Key: String,
    val Type: String,
    val Rank: Int,
    val IsAlias: Boolean
) {
    override fun toString(): String =
        "AccuweatherCityModel(Key='$Key', Type='$Type', Rank=$Rank, IsAlias=$IsAlias)"
}

//ACTUAL VERSION:
//locationKey
//latitude
//longitude
//region
//country
//adminArea
//city