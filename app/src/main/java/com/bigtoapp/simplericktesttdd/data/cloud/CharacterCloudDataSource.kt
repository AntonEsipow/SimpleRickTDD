package com.bigtoapp.simplericktesttdd.data.cloud

import com.bigtoapp.simplericktesttdd.data.character.CharacterData
import com.bigtoapp.simplericktesttdd.data.character.cloud.CharacterService

interface CharacterCloudDataSource {

    suspend fun fetchCharacterDetails(id: String) : CharacterData

    class Base(
        private val service: CharacterService
    ): CharacterCloudDataSource {

        override suspend fun fetchCharacterDetails(id: String): CharacterData {
            val response = service.fetchCharacterDetails(id)
            return response.body() ?: throw IllegalStateException("service unavailable")
        }
    }
}