package com.bigtoapp.simplericktesttdd.details.core.sl

import androidx.lifecycle.ViewModel
import com.bigtoapp.simplericktesttdd.main.sl.Core
import com.bigtoapp.simplericktesttdd.details.presentation.CharacterDetailsViewModel
import com.bigtoapp.simplericktesttdd.details.sl.CharacterDetailsModule

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

        override fun <T : ViewModel> module(clazz: Class<T>): Module<*> =
            if(clazz == CharacterDetailsViewModel.Base::class.java)
                CharacterDetailsModule(core)
            else
                dependencyContainer.module(clazz)
    }
}