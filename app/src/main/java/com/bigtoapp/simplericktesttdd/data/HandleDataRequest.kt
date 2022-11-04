package com.bigtoapp.simplericktesttdd.data

import com.bigtoapp.simplericktesttdd.core.domain.HandleError
import com.bigtoapp.simplericktesttdd.data.character.CharacterData
import com.bigtoapp.simplericktesttdd.domain.character.CharacterDomain

interface HandleDataRequest {

    suspend fun handle(block: suspend () -> CharacterData): CharacterDomain

    class Base(
        private val mapperToDomain: CharacterData.Mapper<CharacterDomain>,
        private val handleError: HandleError<Exception>
    ): HandleDataRequest {

        override suspend fun handle(block: suspend () -> CharacterData): CharacterDomain = try {
            val result = block.invoke()
            result.map(mapperToDomain)
        } catch (e: Exception) {
            throw handleError.handle(e)
        }
    }
}