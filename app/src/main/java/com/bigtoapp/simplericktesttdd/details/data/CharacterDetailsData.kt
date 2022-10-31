package com.bigtoapp.simplericktesttdd.details.data

data class CharacterDetailsData(
    val created: String = "",
    val episode: List<String> = listOf(),
    val gender: String = "",
    val id: Int = 0,
    val image: String = "",
    val location: Location = Location(),
    val name: String = "",
    val origin: Origin = Origin(),
    val species: String = "",
    val status: String = "",
    val type: String = "",
    val url: String = ""
) {
    data class Location(
        val name: String = "",
        val url: String = ""
    )

    data class Origin(
        val name: String = "",
        val url: String = ""
    )

    interface Mapper<T> {
        fun map(created: String, episode: List<String>, gender: String, id: Int,
                image: String, location: Location, name: String, origin: Origin, species: String,
                status: String, type: String, url: String): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(
        created, episode, gender, id, image, location, name, origin, species, status, type, url
    )
}