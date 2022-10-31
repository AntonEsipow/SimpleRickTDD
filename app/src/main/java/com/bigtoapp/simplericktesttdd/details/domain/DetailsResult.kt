package com.bigtoapp.simplericktesttdd.details.domain

sealed class DetailsResult {

    interface Mapper<T> {
        fun map(details: CharacterDetailsDomain, errorMessage: String): T
    }

    abstract fun <T> map(mapper: Mapper<T>): T

    data class Success(private val details: CharacterDetailsDomain = CharacterDetailsDomain()): DetailsResult() {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(details, "")
    }

    data class Failure(private val message: String): DetailsResult() {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(CharacterDetailsDomain(), message)
    }
}
