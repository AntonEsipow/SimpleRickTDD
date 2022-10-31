package com.bigtoapp.simplericktesttdd.details.data

import com.bigtoapp.simplericktesttdd.details.core.domain.NoInternetConnectionException
import com.bigtoapp.simplericktesttdd.details.data.cloud.CharacterDetailsCloudDataSource
import com.bigtoapp.simplericktesttdd.details.domain.CharacterDetailsDomain
import com.bigtoapp.simplericktesttdd.details.domain.CharacterDetailsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class CharacterDetailsRepositoryTest {

    // todo move out of here
    private val characterDetailsData = CharacterDetailsData(
        gender = "Male",
        id = 42,
        name = "Morty Smith",
        status = "Alive",
        species = "Human"
    )

    // todo move out of here
    private val detailsDomain = CharacterDetailsDomain(
        id = "42",
        name = "Morty Smith",
        status = "Alive",
        species = "Human",
        gender = "Male"
    )

    private lateinit var repository: CharacterDetailsRepository
    private lateinit var cloudDataSource: TestCloudDataSource

    @Before
    fun setUp(){
        cloudDataSource = TestCloudDataSource()
        repository = BaseCharactersRepository(
            cloudDataSource,
            HandleDataRequest.Base(
                DetailsDataToDomain(),
                HandleDataError()
            )
        )
    }

    @Test
    fun `test fetch details success`() = runBlocking {
        cloudDataSource.makeExpectedData(characterDetailsData)

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

    private class TestCloudDataSource: CharacterDetailsCloudDataSource {

        private var thereIsConnection = true
        private var detailsData = CharacterDetailsData()
        var detailsCalledCount = 0

        fun changeConnection(connected: Boolean) {
            thereIsConnection = connected
        }

        fun makeExpectedData(data: CharacterDetailsData) {
            detailsData = data
        }

        override suspend fun fetchCharacterDetails(id: String): CharacterDetailsData {
            detailsCalledCount++
            return if(thereIsConnection)
                detailsData
            else
                throw UnknownHostException()
        }
    }
}