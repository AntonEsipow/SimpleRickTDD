package com.bigtoapp.simplericktesttdd.details.data.cloud

import com.bigtoapp.simplericktesttdd.details.data.CharacterDetailsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersService {

    @GET("character/{character-id}")
    suspend fun fetchCharacterDetails(
        @Path("character-id") characterId: String
    ): Response<CharacterDetailsData>
}