package com.bigtoapp.simplericktesttdd.domain

import com.bigtoapp.simplericktesttdd.domain.character.CharacterDomain

sealed class CharacterResult {

    interface Mapper<T> {
        fun map(details: CharacterDomain, errorMessage: String): T
    }

    abstract fun <T> map(mapper: Mapper<T>): T

    data class Success(private val details: CharacterDomain = CharacterDomain()): CharacterResult() {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(details, "")
    }

    data class Failure(private val message: String): CharacterResult() {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(CharacterDomain(), message)
    }
}
