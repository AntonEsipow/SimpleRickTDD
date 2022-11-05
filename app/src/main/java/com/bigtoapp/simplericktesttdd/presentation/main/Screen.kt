package com.bigtoapp.simplericktesttdd.presentation.main

import com.bigtoapp.simplericktesttdd.presentation.character.CharacterFragment

sealed class Screen {

    abstract fun fragment(): Class<out BaseFragment<*>>

    object Character: Screen() {
        override fun fragment(): Class<out BaseFragment<*>> = CharacterFragment::class.java
    }
}