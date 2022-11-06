package com.bigtoapp.simplericktesttdd.data.character.cloud

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
        image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
        status = "Alive",
        gender = "Male",
        species = "Human"
    )

    override suspend fun fetchCharacterDetails(characterId: String): Response<CharacterData> =
        response.makeResponse(characterData)
}