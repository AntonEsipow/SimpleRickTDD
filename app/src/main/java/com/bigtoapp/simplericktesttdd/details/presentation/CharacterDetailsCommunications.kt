package com.bigtoapp.simplericktesttdd.details.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.bigtoapp.simplericktesttdd.details.core.presentation.Communication

interface CharacterDetailsCommunications: ObserveDetails {

    fun showProgress(show: Int)

    fun showState(uiState: UiState)

    fun showDetails(details: CharacterDetailsUi)

    class Base(
        private val progress: ProgressCommunication,
        private val state: DetailsStateCommunication,
        private val showDetails: ShowDetailsCommunication
    ): CharacterDetailsCommunications {

        override fun showProgress(show: Int) = progress.map(show)

        override fun showState(uiState: UiState) = state.map(uiState)

        override fun showDetails(details: CharacterDetailsUi) = showDetails.map(details)

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) =
            progress.observe(owner, observer)

        override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) =
            state.observe(owner, observer)

        override fun observeDetails(owner: LifecycleOwner, observer: Observer<CharacterDetailsUi>) =
            showDetails.observe(owner, observer)
    }
}

interface ProgressCommunication: Communication.Mutable<Int> {
    class Base: Communication.Post<Int>(), ProgressCommunication
}

interface DetailsStateCommunication: Communication.Mutable<UiState>{
    class Base: Communication.Post<UiState>(), DetailsStateCommunication
}

interface ShowDetailsCommunication: Communication.Mutable<CharacterDetailsUi> {
    class Base: Communication.Post<CharacterDetailsUi>(), ShowDetailsCommunication
}