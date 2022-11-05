package com.bigtoapp.simplericktesttdd.core.sl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bigtoapp.simplericktesttdd.sl.DependencyContainer

@Suppress("UNCHECKED_CAST")
class ViewModelsFactory(
    private val dependencyContainer: DependencyContainer
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        dependencyContainer.module(modelClass).viewModel() as T
}