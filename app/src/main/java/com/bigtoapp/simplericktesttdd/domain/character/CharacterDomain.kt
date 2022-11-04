package com.bigtoapp.simplericktesttdd.domain.character

data class CharacterDomain(
    private val id: Int = 0,
    private val name: String = "",
    private val status: String = "",
    private val imageUrl: String = "",
    private val species: String = "",
    private val gender: String = ""
) {

    // todo check where used
    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, name, status, imageUrl, species, mapper.returnIcon(gender))

    interface Mapper<T> {
        // todo think how to refactor
        fun map(id: Int, name: String, status: String, imageUrl: String, species: String, gender: Int): T
        fun returnIcon(gender: String): Int
    }
}
