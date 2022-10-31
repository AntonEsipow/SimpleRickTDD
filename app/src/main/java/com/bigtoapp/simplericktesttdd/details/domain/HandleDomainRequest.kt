package com.bigtoapp.simplericktesttdd.details.domain

import com.bigtoapp.simplericktesttdd.details.core.domain.HandleError

interface HandleDomainRequest {

    suspend fun handle(block: suspend () -> CharacterDetailsDomain): DetailsResult

    class Base(
        private val handleError: HandleError<String>
    ): HandleDomainRequest {

         override suspend fun handle(block: suspend () -> CharacterDetailsDomain): DetailsResult = try {
            DetailsResult.Success(block.invoke())
        } catch (e: Exception) {
            DetailsResult.Failure(handleError.handle(e))
        }
    }
}