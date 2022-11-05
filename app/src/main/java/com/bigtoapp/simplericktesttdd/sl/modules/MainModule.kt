package com.bigtoapp.simplericktesttdd.sl.modules

import com.bigtoapp.simplericktesttdd.core.sl.Module
import com.bigtoapp.simplericktesttdd.presentation.main.MainViewModel
import com.bigtoapp.simplericktesttdd.sl.ProvideNavigation

class MainModule(
    private val provideNavigation: ProvideNavigation
): Module<MainViewModel> {
    override fun viewModel() = MainViewModel(provideNavigation.provideNavigation())
}