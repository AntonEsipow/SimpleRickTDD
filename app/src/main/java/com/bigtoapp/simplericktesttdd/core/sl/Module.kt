package com.bigtoapp.simplericktesttdd.core.sl

import androidx.lifecycle.ViewModel

interface Module<T: ViewModel> {

    fun viewModel(): T
}