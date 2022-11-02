package com.bigtoapp.simplericktesttdd.details.data.cloud

import com.bigtoapp.simplericktesttdd.details.data.CharacterDetailsData

interface CharacterDetailsCloudDataSource {

    suspend fun fetchCharacterDetails(id: String) : CharacterDetailsData

    class Base(
        private val service: CharactersService
        // todo add dependency to map request body
        // release and mock realization
        // make mock service
    ): CharacterDetailsCloudDataSource {

        override suspend fun fetchCharacterDetails(id: String): CharacterDetailsData {
            val response = service.fetchCharacterDetails(id)
            return response.body() ?: throw IllegalStateException("service unavailable")
        }
    }
}