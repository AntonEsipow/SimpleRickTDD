package com.bigtoapp.simplericktesttdd.details.presentation

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailsViewModelTest {


    private lateinit var viewModel: CharacterDetailsViewModel
    private lateinit var interactor: TestCharacterDetailsInteractor
    private lateinit var communications: TestCharacterDetailsCommunication

    @Before
    fun setUp() {
        interactor = TestCharacterDetailsInteractor()
        communications = TestCharacterDetailsCommunication()
        viewModel = CharacterDetailsViewModel(interactor, communications)
    }

     /**
     * Test fetch data and show it successfully
     */
     @Test
     fun `test show data success`() {
         interactor.changeExpectedResult(CharactersResult.Success())
         viewModel.fetchCharacterDetails(id: String)

         assertEquals(View.VISIBLE, communications.progressCalledList[0])
         assertEquals(1, interactor.fetchDetailsCalled)

         assertEquals(2, communications.progressCalledList.size)
         assertEquals(View.GONE, communications.progressCalledList[1])

         assertEquals(1, communications.stateCalledList.size)
//         assertEquals(true, communications.stateCalledList[0] is UiState.Success)
         assertEquals(UiState.Success, communications.stateCalledList[0] )

         assertEquals(1, communications.timesShowDetails)
     }

    /**
     * Test error when get data
     */
    @Test
    fun `test show data error`() {
        interactor.changeExpectedResult(CharactersResult.Failure("no internet connection"))
        viewModel.fetchCharacterDetails(id: String)

        assertEquals(View.VISIBLE, communications.progressCalledList[0])
        assertEquals(1, interactor.fetchDetailsCalled)

        assertEquals(2, communications.progressCalledList.size)
        assertEquals(View.GONE, communications.progressCalledList[1])

        assertEquals(1, communications.stateCalledList.size)
        assertEquals(UiState.ShowError("no internet connection"), communications.stateCalledList[0])

        assertEquals(0, communications.timesShowDetails)
    }

    private class TestCharacterDetailsInteractor: CharacterDetailsInteractor {

        private var result: CharactersResult = CharactersResult.Success()
        var fetchDetailsCalled = 0

        fun changeExpectedResult(newResult: CharactersResult) {
            result = newResult
        }

        override fun fecthCharacterDetails(id: String): CharacterDeatilsResult {
            fetchDetailsCalled++
            return result
        }
    }

    private class TestCharacterDetailsCommunication: CharactersDetailsCommunication {

        val progressCalledList = mutableListOf<Int>()
        val stateCalledList = mutableListOf<UiState>()
        var timesShowDetails = 0
        val characterDetails = CharacterDetailsUi()

        override fun showProgress(show: Int) {
            progressCalledList.add(show)
        }

        override fun showState(uiState: UiState) {
            stateCalledList.add(uiState)
        }

        override fun showDetails(details: CharacterDetailsUi) {
            timesShowDetails++
            characterDetails.addAll(list)
        }

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) = Unit
        override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) = Unit
        override fun observeCharacterDetails(owner: LifecycleOwner, observer: Observer<CharacterDetailsUi>) = Unit
    }
}