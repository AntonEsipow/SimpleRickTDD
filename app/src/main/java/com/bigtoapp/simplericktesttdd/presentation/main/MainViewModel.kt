package com.bigtoapp.simplericktesttdd.presentation.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.bigtoapp.simplericktesttdd.core.presentation.Communication
import com.bigtoapp.simplericktesttdd.core.presentation.NavigationStrategy

class MainViewModel(
    private val navigationCommunication: NavigationCommunication.Mutable
): ViewModel(), Init, Communication.Observe<NavigationStrategy> {

    override fun init(isFirstRun: Boolean) {
        if(isFirstRun)
            navigationCommunication.map(NavigationStrategy.Replace(Screen.Character))
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<NavigationStrategy>) =
        navigationCommunication.observe(owner, observer)
}