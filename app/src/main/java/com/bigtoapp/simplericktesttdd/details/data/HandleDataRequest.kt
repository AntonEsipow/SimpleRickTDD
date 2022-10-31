package com.bigtoapp.simplericktesttdd.details.data

import com.bigtoapp.simplericktesttdd.details.core.domain.HandleError
import com.bigtoapp.simplericktesttdd.details.domain.CharacterDetailsDomain

interface HandleDataRequest {

    suspend fun handle(block: suspend () -> CharacterDetailsData): CharacterDetailsDomain

    class Base(
        private val mapperToDomain: CharacterDetailsData.Mapper<CharacterDetailsDomain>,
        private val handleError: HandleError<Exception>
    ): HandleDataRequest {

        override suspend fun handle(block: suspend () -> CharacterDetailsData): CharacterDetailsDomain = try {
            val result = block.invoke()
            result.map(mapperToDomain)
        } catch (e: Exception) {
            throw handleError.handle(e)
        }
    }
}