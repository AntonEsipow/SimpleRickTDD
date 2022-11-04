package com.bigtoapp.simplericktesttdd.data

import com.bigtoapp.simplericktesttdd.core.domain.NoInternetConnectionException
import com.bigtoapp.simplericktesttdd.data.character.CharacterData
import com.bigtoapp.simplericktesttdd.data.character.CharacterDataToDomain
import com.bigtoapp.simplericktesttdd.data.cloud.CharacterCloudDataSource
import com.bigtoapp.simplericktesttdd.domain.character.CharacterDomain
import com.bigtoapp.simplericktesttdd.domain.CharacterRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class CharacterRepositoryTest {

    // todo move out of here
    private val characterData = CharacterData(
        gender = "Male",
        id = 42,
        name = "Morty Smith",
        status = "Alive",
        species = "Human"
    )

    // todo move out of here
    private val detailsDomain = CharacterDomain(
        id = "42",
        name = "Morty Smith",
        status = "Alive",
        species = "Human",
        gender = "Male"
    )

    private lateinit var repository: CharacterRepository
    private lateinit var cloudDataSource: TestCloudDataSource

    @Before
    fun setUp(){
        cloudDataSource = TestCloudDataSource()
        repository = BaseCharactersRepository(
            cloudDataSource,
            HandleDataRequest.Base(
                CharacterDataToDomain(),
                HandleDataError()
            )
        )
    }

    @Test
    fun `test fetch details success`() = runBlocking {
        cloudDataSource.makeExpectedData(characterData)

        val actual = repository.fetchCharacterDetails("42")
        val expected = detailsDomain

        assertEquals(expected, actual)
        assertEquals(1, cloudDataSource.detailsCalledCount)
    }

    @Test(expected = NoInternetConnectionException::class)
    fun `test fetch details failure`() = runBlocking {
        cloudDataSource.changeConnection(false)

        repository.fetchCharacterDetails("42")
        assertEquals(1, cloudDataSource.detailsCalledCount)
    }

    private class TestCloudDataSource: CharacterCloudDataSource {

        private var thereIsConnection = true
        private var detailsData = CharacterData()
        var detailsCalledCount = 0

        fun changeConnection(connected: Boolean) {
            thereIsConnection = connected
        }

        fun makeExpectedData(data: CharacterData) {
            detailsData = data
        }

        override suspend fun fetchCharacterDetails(id: String): CharacterData {
            detailsCalledCount++
            return if(thereIsConnection)
                detailsData
            else
                throw UnknownHostException()
        }
    }
}