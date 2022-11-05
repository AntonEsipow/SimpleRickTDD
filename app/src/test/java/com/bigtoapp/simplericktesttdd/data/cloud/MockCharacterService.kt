package com.bigtoapp.simplericktesttdd.data.cloud

import com.bigtoapp.simplericktesttdd.core.data.MockResponse
import com.bigtoapp.simplericktesttdd.data.character.CharacterData
import com.bigtoapp.simplericktesttdd.data.character.cloud.CharacterService
import retrofit2.Response

class MockCharacterService(
    private val response: MockResponse<CharacterData>
): CharacterService {

    private val characterData = CharacterData(
        id = 1,
        name = "Morty Smith",
        status = "Alive",
        gender = "Male",
        species = "human"
    )

    override suspend fun fetchCharacterDetails(characterId: String): Response<CharacterData> =
        response.makeResponse(characterData)
}