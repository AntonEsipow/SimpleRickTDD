package com.bigtoapp.simplericktesttdd.domain

import com.bigtoapp.simplericktesttdd.core.domain.HandleError
import com.bigtoapp.simplericktesttdd.domain.character.CharacterDomain

interface HandleDomainRequest {

    suspend fun handle(block: suspend () -> CharacterDomain): CharacterResult

    class Base(
        private val handleError: HandleError<String>
    ): HandleDomainRequest {

         override suspend fun handle(block: suspend () -> CharacterDomain): CharacterResult = try {
            CharacterResult.Success(block.invoke())
        } catch (e: Exception) {
            CharacterResult.Failure(handleError.handle(e))
        }
    }
}