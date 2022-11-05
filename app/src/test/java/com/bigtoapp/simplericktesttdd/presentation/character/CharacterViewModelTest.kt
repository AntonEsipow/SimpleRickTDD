package com.bigtoapp.simplericktesttdd.presentation.character

import android.view.View
import com.bigtoapp.simplericktesttdd.core.presentation.DispatchersList
import com.bigtoapp.simplericktesttdd.domain.CharacterInteractor
import com.bigtoapp.simplericktesttdd.domain.CharacterResult
import com.bigtoapp.simplericktesttdd.presentation.BaseTest
import com.bigtoapp.simplericktesttdd.presentation.CharacterResultMapper
import com.bigtoapp.simplericktesttdd.presentation.HandleCharacterRequest
import com.bigtoapp.simplericktesttdd.presentation.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CharacterViewModelTest: BaseTest() {


    private lateinit var viewModel: CharacterViewModel
    private lateinit var interactor: TestCharacterDetailsInteractor
    private lateinit var communications: TestCharacterDetailsCommunication
    private lateinit var testDomainToUi: TestCharacterDomainToUi

    @Before
    fun setUp() {
        interactor = TestCharacterDetailsInteractor()
        communications = TestCharacterDetailsCommunication()
        testDomainToUi = TestCharacterDomainToUi()
        viewModel = CharacterViewModel.Base(
            HandleCharacterRequest.Base(
                TestDispatchersList(),
                communications,
                CharacterResultMapper(communications, testDomainToUi)
            ),
            communications,
            interactor
        )
    }

     /**
     * Test fetch data and show it successfully
     */
     @Test
     fun `test show data success`() = runBlocking {
         interactor.changeExpectedResult(CharacterResult.Success(detailsDomain))
         viewModel.fetchCharacterDetails("42")

         assertEquals(View.VISIBLE, communications.progressCalledList[0])
         assertEquals(1, interactor.detailsCalledItem.size)
         assertEquals(
             CharacterResult.Success(detailsDomain),
             interactor.detailsCalledItem[0]
         )

         assertEquals(2, communications.progressCalledList.size)
         assertEquals(View.GONE, communications.progressCalledList[1])

         assertEquals(1, communications.stateCalledList.size)
         assertEquals(UiState.Success, communications.stateCalledList[0] )

         assertEquals(1, communications.timesShowDetails)
         assertEquals(detailsUi, communications.detailsUiList[0])
     }

    /**
     * Test error when get data
     */
    @Test
    fun `test show data error`() = runBlocking {
        interactor.changeExpectedResult(CharacterResult.Failure("no internet connection"))
        viewModel.fetchCharacterDetails("42")

        assertEquals(View.VISIBLE, communications.progressCalledList[0])
        assertEquals(1, interactor.detailsCalledItem.size)

        assertEquals(2, communications.progressCalledList.size)
        assertEquals(View.GONE, communications.progressCalledList[1])

        assertEquals(1, communications.stateCalledList.size)
        assertEquals(UiState.ShowError("no internet connection"), communications.stateCalledList[0])

        assertEquals(0, communications.timesShowDetails)
    }

    private class TestCharacterDetailsInteractor: CharacterInteractor {

        private var result: CharacterResult = CharacterResult.Success()
        var detailsCalledItem = mutableListOf<CharacterResult>()

        fun changeExpectedResult(newResult: CharacterResult) {
            result = newResult
        }

        override suspend fun fetchCharacterDetails(id: String): CharacterResult {
            detailsCalledItem.add(result)
            return result
        }
    }

    private class TestDispatchersList(
        private val dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    ) : DispatchersList {

        override fun io(): CoroutineDispatcher = dispatcher
        override fun ui(): CoroutineDispatcher = dispatcher
    }
}