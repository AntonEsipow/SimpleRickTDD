package com.bigtoapp.simplericktesttdd.core.presentation

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bigtoapp.simplericktesttdd.presentation.main.Screen

interface NavigationStrategy {

    fun navigate(supportFragmentManager: FragmentManager, containerId: Int)

    abstract class Abstract(protected open val screen: Screen): NavigationStrategy {

        override fun navigate(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.beginTransaction()
                .executeTransaction(containerId)
                .commit()
        }

        protected abstract fun FragmentTransaction
                .executeTransaction(containerId: Int): FragmentTransaction
    }

    data class Replace(override val screen: Screen): Abstract(screen) {
        override fun FragmentTransaction.executeTransaction(containerId: Int) =
            replace(containerId, screen.fragment().newInstance())
    }

    data class Add(override val screen: Screen): Abstract(screen) {
        override fun FragmentTransaction.executeTransaction(containerId: Int) =
            add(containerId, screen.fragment().newInstance())
    }
}