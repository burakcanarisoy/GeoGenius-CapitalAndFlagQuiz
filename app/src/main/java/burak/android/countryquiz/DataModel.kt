package burak.android.countryquiz

data class Country(
    val name: Name,
    val capital: List<String>?,
    val flags: Flags,
    val difficulty: String
)
data class Name(
    val common: String
)
data class Flags(
    val png: String
)
