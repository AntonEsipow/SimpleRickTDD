package com.bigtoapp.simplericktesttdd.details.core.sl

import androidx.lifecycle.ViewModel

interface Module<T: ViewModel> {

    fun viewModel(): T
}