package com.bigtoapp.simplericktesttdd.details.domain

interface CharacterDetailsRepository {

    suspend fun fetchCharacterDetails(id: String): CharacterDetailsDomain
}