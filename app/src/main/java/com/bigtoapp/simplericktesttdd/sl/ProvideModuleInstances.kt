package com.bigtoapp.simplericktesttdd.sl

import com.bigtoapp.simplericktesttdd.data.cloud.CloudModule

interface ProvideModuleInstances {

    // todo provide cache module later
    fun provideCloudModule(): CloudModule

    class Debug(): ProvideModuleInstances {
        override fun provideCloudModule(): CloudModule = CloudModule.Debug()
    }

    // todo provide Release and Mock Instances
}