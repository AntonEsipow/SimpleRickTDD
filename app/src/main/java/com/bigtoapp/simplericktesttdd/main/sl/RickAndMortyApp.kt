package com.bigtoapp.simplericktesttdd.main.sl

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.bigtoapp.simplericktesttdd.details.core.sl.DependencyContainer
import com.bigtoapp.simplericktesttdd.details.core.sl.ProvideViewModel
import com.bigtoapp.simplericktesttdd.details.core.sl.ViewModelsFactory

class RickAndMortyApp: Application(), ProvideViewModel {

    private lateinit var viewModelsFactory: ViewModelsFactory

    override fun onCreate() {
        super.onCreate()
        // todo provide different instances out of configuration
        val provideInstances = ProvideModuleInstances.Debug()
        viewModelsFactory = ViewModelsFactory(
            DependencyContainer.Base(Core.Base(this, provideInstances))
        )

    }

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, viewModelsFactory)[clazz]
}