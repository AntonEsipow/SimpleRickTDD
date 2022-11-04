package com.bigtoapp.simplericktesttdd.domain

import com.bigtoapp.simplericktesttdd.core.domain.HandleError
import com.bigtoapp.simplericktesttdd.core.domain.NoInternetConnectionException
import com.bigtoapp.simplericktesttdd.core.presentation.ManageResources
import com.bigtoapp.simplericktesttdd.domain.character.CharacterDomain
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterInteractorTest {

    private lateinit var interactor: CharacterInteractor
    private lateinit var repository: TestCharactersRepository
    private lateinit var manageResources: TestManageResources

    private val detailsDomain = CharacterDomain(
        id = "42",
        name = "Morty Smith",
        status = "Alive",
        species = "Human",
        gender = "Male"
    )

    @Before
    fun setUp() {
        manageResources = TestManageResources()
        repository = TestCharactersRepository()
        interactor = CharacterInteractor.Base(
            repository,
            HandleDomainRequest.Base(HandleError.Base(manageResources))
        )
    }

    @Test
    fun `test fetch character details success`(): Unit = runBlocking {
        repository.changeExpectedDetailsOfCharacter(detailsDomain)

        val actual = interactor.fetchCharacterDetails("42")
        val expected = CharacterResult.Success(detailsDomain)

        assertEquals(expected, actual)
        assertEquals("42", repository.characterDetailsCalledList[0])
        assertEquals(1, repository.characterDetailsCalledList.size)
    }

    @Test
    fun `test fetch character details error`(): Unit = runBlocking {
        repository.expectingErrorGetCharacterDetails(true)
        manageResources.changeExpected("no internet connection")

        val actual = interactor.fetchCharacterDetails("42")
        val expected = CharacterResult.Failure("no internet connection")

        assertEquals(expected, actual)
        assertEquals("42", repository.characterDetailsCalledList[0])
        assertEquals(1, repository.characterDetailsCalledList.size)
    }

    private class TestCharactersRepository: CharacterRepository {

        private var detailsDomain = CharacterDomain()
        private var errorWhenCharacterDetails = false
        val characterDetailsCalledList = mutableListOf<String>()

        fun changeExpectedDetailsOfCharacter(detailsDomain: CharacterDomain) {
            this.detailsDomain = detailsDomain
        }

        fun expectingErrorGetCharacterDetails(error: Boolean) {
            errorWhenCharacterDetails = error
        }

        override suspend fun fetchCharacterDetails(id: String): CharacterDomain {
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