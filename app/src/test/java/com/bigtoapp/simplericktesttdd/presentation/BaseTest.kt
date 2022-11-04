package com.bigtoapp.simplericktesttdd.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.bigtoapp.simplericktesttdd.domain.character.CharacterDomain

abstract class BaseTest {

    protected val detailsDomain = CharacterDomain(
        id = "42",
        name = "Morty Smith",
        status = "Alive",
        species = "Human",
        gender = "male"
    )

    protected val detailsUi = CharacterDetailsUi(
        id = "42",
        name = "Morty Smith",
        status = "Alive",
        species = "Human",
        gender = "male"
    )

    protected class TestCharacterDetailsCommunication: CharacterDetailsCommunications {

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
}