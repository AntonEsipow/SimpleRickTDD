package com.bigtoapp.simplericktesttdd.presentation.character

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bigtoapp.simplericktesttdd.domain.CharacterInteractor
import com.bigtoapp.simplericktesttdd.presentation.*

interface CharacterViewModel: ObserveCharacter {

    fun fetchCharacterDetails(id: String)

    class Base(
        private val handleRequest: HandleCharacterRequest,
        private val communications: CharacterDetailsCommunications,
        private val interactor: CharacterInteractor
    ): ViewModel(), CharacterViewModel {

        override fun fetchCharacterDetails(id: String) = handleRequest.handle(viewModelScope) {
            interactor.fetchCharacterDetails(id)
        }

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) =
            communications.observeProgress(owner, observer)

        override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) =
            communications.observeState(owner, observer)

        override fun observeDetails(owner: LifecycleOwner, observer: Observer<CharacterDetailsUi>) =
            communications.observeDetails(owner, observer)
    }
}