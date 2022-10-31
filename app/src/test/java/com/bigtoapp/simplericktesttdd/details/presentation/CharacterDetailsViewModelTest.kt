package com.bigtoapp.simplericktesttdd.details.presentation

import android.view.View
import com.bigtoapp.simplericktesttdd.details.core.presentation.DispatchersList
import com.bigtoapp.simplericktesttdd.details.domain.CharacterDetailsInteractor
import com.bigtoapp.simplericktesttdd.details.domain.DetailsResult
import com.bigtoapp.simplericktesttdd.details.domain.DetailsDomainToUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CharacterDetailsViewModelTest: BaseTest() {


    private lateinit var viewModel: CharacterDetailsViewModel
    private lateinit var interactor: TestCharacterDetailsInteractor
    private lateinit var communications: TestCharacterDetailsCommunication

    @Before
    fun setUp() {
        interactor = TestCharacterDetailsInteractor()
        communications = TestCharacterDetailsCommunication()
        viewModel = CharacterDetailsViewModel.Base(
            HandleCharacterRequest.Base(
                TestDispatchersList(),
                communications,
                DetailsResultMapper(communications, DetailsDomainToUi())
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
         interactor.changeExpectedResult(DetailsResult.Success(detailsDomain))
         viewModel.fetchCharacterDetails("42")

         assertEquals(View.VISIBLE, communications.progressCalledList[0])
         assertEquals(1, interactor.detailsCalledItem.size)
         assertEquals(
             DetailsResult.Success(detailsDomain),
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
        interactor.changeExpectedResult(DetailsResult.Failure("no internet connection"))
        viewModel.fetchCharacterDetails("42")

        assertEquals(View.VISIBLE, communications.progressCalledList[0])
        assertEquals(1, interactor.detailsCalledItem.size)

        assertEquals(2, communications.progressCalledList.size)
        assertEquals(View.GONE, communications.progressCalledList[1])

        assertEquals(1, communications.stateCalledList.size)
        assertEquals(UiState.ShowError("no internet connection"), communications.stateCalledList[0])

        assertEquals(0, communications.timesShowDetails)
    }

    private class TestCharacterDetailsInteractor: CharacterDetailsInteractor {

        private var result: DetailsResult = DetailsResult.Success()
        var detailsCalledItem = mutableListOf<DetailsResult>()

        fun changeExpectedResult(newResult: DetailsResult) {
            result = newResult
        }

        override suspend fun fetchCharacterDetails(id: String): DetailsResult {
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