package com.bigtoapp.simplericktesttdd.domain

interface CharacterInteractor {

    suspend fun fetchCharacterDetails(id: String): CharacterResult

    class Base(
        private val repository: CharacterRepository,
        private val handleRequest: HandleDomainRequest
    ) : CharacterInteractor {

        override suspend fun fetchCharacterDetails(id: String) = handleRequest.handle {
            repository.fetchCharacterDetails(id)
        }
    }
}