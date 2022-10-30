package com.bigtoapp.simplericktesttdd.details.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bigtoapp.simplericktesttdd.details.domain.CharacterDetailsInteractor

interface CharacterDetailsViewModel: ObserveDetails {

    fun fetchCharacterDetails(id: String)

    class Base(
        private val handleRequest: HandleCharacterRequest,
        private val communications: CharacterDetailsCommunications,
        private val interactor: CharacterDetailsInteractor
    ): ViewModel(), CharacterDetailsViewModel {

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