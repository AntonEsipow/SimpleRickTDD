package com.bigtoapp.simplericktesttdd.core.sl

import androidx.lifecycle.ViewModel
import com.bigtoapp.simplericktesttdd.sl.Core
import com.bigtoapp.simplericktesttdd.presentation.CharacterViewModel
import com.bigtoapp.simplericktesttdd.sl.CharacterModule

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
            if(clazz == CharacterViewModel.Base::class.java)
                CharacterModule(core)
            else
                dependencyContainer.module(clazz)
    }
}