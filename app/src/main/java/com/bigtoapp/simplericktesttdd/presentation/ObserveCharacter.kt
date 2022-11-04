package com.bigtoapp.simplericktesttdd.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface ObserveCharacter {

    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>)

    fun observeState(owner: LifecycleOwner, observer: Observer<UiState>)

    fun observeDetails(owner: LifecycleOwner, observer: Observer<CharacterDetailsUi>)
}