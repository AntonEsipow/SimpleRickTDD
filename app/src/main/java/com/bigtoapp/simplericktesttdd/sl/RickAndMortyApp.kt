package com.bigtoapp.simplericktesttdd.sl

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.bigtoapp.simplericktesttdd.BuildConfig
import com.bigtoapp.simplericktesttdd.BuildConfig.DEBUG
import com.bigtoapp.simplericktesttdd.core.sl.ProvideViewModel
import com.bigtoapp.simplericktesttdd.core.sl.ViewModelsFactory

class RickAndMortyApp: Application(), ProvideViewModel {

    private lateinit var viewModelsFactory: ViewModelsFactory

    override fun onCreate() {
        super.onCreate()
        // todo change instances when release/mock/debug
        val provideInstances = when(BuildConfig.BUILD_TYPE) {
            "release" -> ProvideModuleInstances.Release()
            "debug" -> ProvideModuleInstances.Debug()
            else -> ProvideModuleInstances.Mock()
        }
        viewModelsFactory = ViewModelsFactory(
            DependencyContainer.Base(Core.Base(this, provideInstances))
        )

    }

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, viewModelsFactory)[clazz]
}