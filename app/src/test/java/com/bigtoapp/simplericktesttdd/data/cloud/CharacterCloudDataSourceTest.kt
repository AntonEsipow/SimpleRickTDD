package com.bigtoapp.simplericktesttdd.data.cloud

import com.bigtoapp.simplericktesttdd.data.character.CharacterData
import com.bigtoapp.simplericktesttdd.data.character.cloud.MockCharacterService
import com.bigtoapp.simplericktesttdd.data.character.cloud.MockCharacterResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterCloudDataSourceTest {

    private val characterData = CharacterData(
        id = 1,
        name = "Morty Smith",
        status = "Alive",
        image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
        gender = "Male",
        species = "Human"
    )

    @Test
    fun `test fetch data`() = runBlocking{
        val response = MockCharacterResponse()
        val service = MockCharacterService(response)
        val dataSource = CharacterCloudDataSource.Base(service)

        val actual = dataSource.fetchCharacterDetails("")
        val expected = characterData
        assertEquals(expected, actual)
    }
}