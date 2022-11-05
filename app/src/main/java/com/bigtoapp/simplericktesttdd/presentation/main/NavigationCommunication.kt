package com.bigtoapp.simplericktesttdd.presentation.main

import com.bigtoapp.simplericktesttdd.core.presentation.Communication
import com.bigtoapp.simplericktesttdd.core.presentation.NavigationStrategy

interface NavigationCommunication {

    interface Observe : Communication.Observe<NavigationStrategy>

    interface Mutate: Communication.Mutate<NavigationStrategy>

    interface Mutable: Observe, Mutate

    class Base(): Communication.SingleUi<NavigationStrategy>(), Mutable
}