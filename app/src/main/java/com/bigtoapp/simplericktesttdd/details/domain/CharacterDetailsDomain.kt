package com.bigtoapp.simplericktesttdd.details.domain

data class CharacterDetailsDomain(
    private val id: String = "",
    private val name: String = "",
    private val status: String = "",
    private val species: String = "",
    private val gender: String = ""
) {

    // todo check where used
    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, name, status, species, gender)

    interface Mapper<T> {
        // todo think how to refactor
        fun map(id: String, name: String, status: String, species: String, gender: String): T
    }
}
