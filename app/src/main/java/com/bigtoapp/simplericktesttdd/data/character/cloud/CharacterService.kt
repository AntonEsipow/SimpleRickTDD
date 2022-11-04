package com.bigtoapp.simplericktesttdd.data.character.cloud

import com.bigtoapp.simplericktesttdd.data.character.CharacterData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("character/{character-id}")
    suspend fun fetchCharacterDetails(
        @Path("character-id") characterId: String
    ): Response<CharacterData>
}