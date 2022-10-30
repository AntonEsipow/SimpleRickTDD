package com.bigtoapp.simplericktesttdd.details.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface ObserveDetails {

    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>)

    fun observeState(owner: LifecycleOwner, observer: Observer<UiState>)

    fun observeDetails(owner: LifecycleOwner, observer: Observer<CharacterDetailsUi>)
}