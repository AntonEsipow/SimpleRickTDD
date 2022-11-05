package com.bigtoapp.simplericktesttdd.sl

import androidx.lifecycle.ViewModel
import com.bigtoapp.simplericktesttdd.core.sl.Module
import com.bigtoapp.simplericktesttdd.presentation.character.CharacterViewModel
import com.bigtoapp.simplericktesttdd.presentation.main.MainViewModel
import com.bigtoapp.simplericktesttdd.sl.modules.CharacterModule
import com.bigtoapp.simplericktesttdd.sl.modules.MainModule

interface DependencyContainer {

    fun <T: ViewModel> module(clazz: Class<T>): Module<*>

    class Error(): DependencyContainer {
        override fun <T : ViewModel> module(clazz: Class<T>): Module<*> =
            throw IllegalStateException("no module found for $clazz")
    }

    class Base(
        private val core: Core,
        private val dependencyContainer: DependencyContainer = Error()
    ): DependencyContainer {

        override fun <T : ViewModel> module(clazz: Class<T>): Module<*> = when(clazz) {
            MainViewModel::class.java -> MainModule(core)
            CharacterViewModel.Base::class.java -> CharacterModule(core)
            else -> dependencyContainer.module(clazz)
        }
    }
}