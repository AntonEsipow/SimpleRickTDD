package com.bigtoapp.simplericktesttdd.main.sl

import android.content.Context
import com.bigtoapp.simplericktesttdd.details.data.cloud.CloudModule

interface ProvideModuleInstances {

    // todo provide cache module later
    fun provideCloudModule(): CloudModule

    class Debug(): ProvideModuleInstances {
        override fun provideCloudModule(): CloudModule = CloudModule.Debug()
    }

    // todo provide Release and Mock Instances
}