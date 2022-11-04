package com.bigtoapp.simplericktesttdd.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.bigtoapp.simplericktesttdd.R
import com.bigtoapp.simplericktesttdd.domain.character.CharacterDomain
import com.bigtoapp.simplericktesttdd.domain.character.CharacterDomainToUi

abstract class BaseTest {

    protected val detailsDomain = CharacterDomain(
        id = 42,
        name = "Morty Smith",
        status = "Alive",
        imageUrl = "url",
        species = "Human",
        gender = "male"
    )

    protected val detailsUi = CharacterDetailsUi(
        id = "42",
        name = "Morty Smith",
        status = "Alive",
        imageUrl = "url",
        species = "Human",
        gender = 1
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

    protected class TestCharacterDomainToUi: CharacterDomain.Mapper<CharacterDetailsUi> {

        override fun map(
            id: Int,
            name: String,
            status: String,
            imageUrl: String,
            species: String,
            gender: Int
        ) = CharacterDetailsUi(id.toString(), name, status, imageUrl, species, gender)

        override fun returnIcon(gender: String): Int {
            return when (gender.lowercase()) {
                "male" -> 1
                "female" -> 2
                else -> 3
            }
        }
    }
}