package com.bigtoapp.simplericktesttdd.sl

import android.content.Context
import com.bigtoapp.simplericktesttdd.core.presentation.DispatchersList
import com.bigtoapp.simplericktesttdd.core.presentation.ManageResources
import com.bigtoapp.simplericktesttdd.data.cloud.CloudModule

interface Core: CloudModule, ManageResources {

    fun provideDispatchers(): DispatchersList

    class Base(
        context: Context,
        private val provideModuleInstances: ProvideModuleInstances
    ): Core {

        private val manageResources: ManageResources = ManageResources.Base(context)

        private val dispatchersList by lazy {
            DispatchersList.Base()
        }

        private val cloudModule by lazy {
            provideModuleInstances.provideCloudModule()
        }

        override fun string(id: Int) = manageResources.string(id)

        override fun provideDispatchers() = dispatchersList

        override fun <T> service(clasz: Class<T>): T = cloudModule.service(clasz)
    }
}