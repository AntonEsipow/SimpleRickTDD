package com.bigtoapp.simplericktesttdd.data.cloud

import com.bigtoapp.simplericktesttdd.data.character.CharacterData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterCloudDataSourceTest {

    private val characterData = CharacterData(
        id = 1,
        name = "Morty Smith",
        status = "Alive",
        gender = "Male",
        species = "human"
    )

    @Test
    fun `test fetch data`() = runBlocking{
        val response = MockResponse()
        val service = MockCharacterService(response)
        val dataSource = CharacterCloudDataSource.Base(service)

        val actual = dataSource.fetchCharacterDetails("")
        val expected = characterData
        assertEquals(expected, actual)
    }
}