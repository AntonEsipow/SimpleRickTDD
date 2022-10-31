package com.bigtoapp.simplericktesttdd.details.domain

import com.bigtoapp.simplericktesttdd.details.core.domain.HandleError
import com.bigtoapp.simplericktesttdd.details.core.domain.NoInternetConnectionException
import com.bigtoapp.simplericktesttdd.details.core.presentation.ManageResources
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterDetailsInteractorTest {

    private lateinit var interactor: CharacterDetailsInteractor
    private lateinit var repository: TestCharactersRepository
    private lateinit var manageResources: TestManageResources

    protected val detailsDomain = CharacterDetailsDomain(
        id = "42",
        name = "Morty Smith",
        status = "Alive",
        species = "Human",
        gender = "male"
    )

    @Before
    fun setUp() {
        manageResources = TestManageResources()
        repository = TestCharactersRepository()
        interactor = CharacterDetailsInteractor.Base(
            repository,
            HandleDomainRequest.Base(HandleError.Base(manageResources))
        )
    }

    @Test
    fun `test fetch character details success`(): Unit = runBlocking {
        repository.changeExpectedDetailsOfCharacter(detailsDomain)

        val actual = interactor.fetchCharacterDetails("42")
        val expected = DetailsResult.Success(detailsDomain)

        assertEquals(expected, actual)
        assertEquals("42", repository.characterDetailsCalledList[0])
        assertEquals(1, repository.characterDetailsCalledList.size)
    }

    @Test
    fun `test fetch character details error`(): Unit = runBlocking {
        repository.expectingErrorGetCharacterDetails(true)
        manageResources.changeExpected("no internet connection")

        val actual = interactor.fetchCharacterDetails("42")
        val expected = DetailsResult.Failure("no internet connection")

        assertEquals(expected, actual)
        assertEquals("42", repository.characterDetailsCalledList[0])
        assertEquals(1, repository.characterDetailsCalledList.size)
    }

    private class TestCharactersRepository: CharacterDetailsRepository {

        private var detailsDomain = CharacterDetailsDomain()
        private var errorWhenCharacterDetails = false
        val characterDetailsCalledList = mutableListOf<String>()

        fun changeExpectedDetailsOfCharacter(detailsDomain: CharacterDetailsDomain) {
            this.detailsDomain = detailsDomain
        }

        fun expectingErrorGetCharacterDetails(error: Boolean) {
            errorWhenCharacterDetails = error
        }

        override suspend fun fetchCharacterDetails(id: String): CharacterDetailsDomain {
            characterDetailsCalledList.add(id)
            if(errorWhenCharacterDetails)
                throw NoInternetConnectionException()
            else
                return detailsDomain
        }
    }

    private class TestManageResources: ManageResources {

        private var value = ""

        fun changeExpected(string: String) {
            value = string
        }

        override fun string(id: Int): String = value

    }
}