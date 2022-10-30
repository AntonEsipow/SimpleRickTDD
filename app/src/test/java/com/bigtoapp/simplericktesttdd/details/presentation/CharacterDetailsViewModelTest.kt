package com.bigtoapp.simplericktesttdd.details.presentation

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.bigtoapp.simplericktesttdd.details.core.DispatchersList
import com.bigtoapp.simplericktesttdd.details.domain.CharacterDetailsDomain
import com.bigtoapp.simplericktesttdd.details.domain.CharacterDetailsInteractor
import com.bigtoapp.simplericktesttdd.details.domain.DetailsUiMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CharacterDetailsViewModelTest {


    private lateinit var viewModel: CharacterDetailsViewModel
    private lateinit var interactor: TestCharacterDetailsInteractor
    private lateinit var communications: TestCharacterDetailsCommunication

    private val detailsDomain = CharacterDetailsDomain(
        id = "42",
        name = "Morty Smith",
        status = "Alive",
        species = "Human",
        gender = "male"
    )

    private val detailsUi = CharacterDetailsUi(
        id = "42",
        name = "Morty Smith",
        status = "Alive",
        species = "Human",
        gender = "male"
    )

    @Before
    fun setUp() {
        interactor = TestCharacterDetailsInteractor()
        communications = TestCharacterDetailsCommunication()
        viewModel = CharacterDetailsViewModel.Base(
            HandleCharacterRequest.Base(
                TestDispatchersList(),
                communications,
                DetailsResultMapper(communications, DetailsUiMapper())
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
//         assertEquals(true, communications.stateCalledList[0] is UiState.Success)
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

    private class TestCharacterDetailsCommunication: CharacterDetailsCommunications {

        val progressCalledList = mutableListOf<Int>()
        val stateCalledList = mutableListOf<UiState>()
        var timesShowDetails = 0
        val detailsUiList = mutableListOf<CharacterDetailsUi>()

        override fun showProgress(show: Int) {
            progressCalledList.add(show)
        }

        override fun showState(uiState: UiState) {
            stateCalledList.add(uiState)
        }

        override fun showDetails(details: CharacterDetailsUi) {
            detailsUiList.add(details)
            timesShowDetails++
        }

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) = Unit
        override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) = Unit
        override fun observeDetails(owner: LifecycleOwner, observer: Observer<CharacterDetailsUi>) = Unit
    }

    private class TestDispatchersList(
        private val dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    ) : DispatchersList {

        override fun io(): CoroutineDispatcher = dispatcher
        override fun ui(): CoroutineDispatcher = dispatcher
    }
}