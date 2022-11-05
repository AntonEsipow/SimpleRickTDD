package com.bigtoapp.simplericktesttdd.presentation.main

import com.bigtoapp.simplericktesttdd.core.presentation.NavigationStrategy
import com.bigtoapp.simplericktesttdd.presentation.BaseTest
import org.junit.Assert.assertEquals
import org.junit.Test

class MainViewModelTest: BaseTest() {

    @Test
    fun `test navigation at start`() {
        val navigation = TestNavigationCommunication()
        val viewModel = MainViewModel(navigation)

        viewModel.init(true)
        assertEquals(1, navigation.count)
        assertEquals(NavigationStrategy.Replace(Screen.Character), navigation.strategy)

        viewModel.init(false)
        assertEquals(1, navigation.count)
    }
}