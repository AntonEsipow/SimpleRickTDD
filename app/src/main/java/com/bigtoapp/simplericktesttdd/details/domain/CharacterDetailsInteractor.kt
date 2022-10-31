package com.bigtoapp.simplericktesttdd.details.domain

interface CharacterDetailsInteractor {

    suspend fun fetchCharacterDetails(id: String): DetailsResult

    class Base(
        private val repository: CharacterDetailsRepository,
        private val handleRequest: HandleDomainRequest
    ) : CharacterDetailsInteractor {

        override suspend fun fetchCharacterDetails(id: String) = handleRequest.handle {
            repository.fetchCharacterDetails(id)
        }
    }
}