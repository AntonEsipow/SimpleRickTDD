package com.bigtoapp.simplericktesttdd.core.domain

import com.bigtoapp.simplericktesttdd.R
import com.bigtoapp.simplericktesttdd.core.presentation.ManageResources

interface HandleError<T> {

    fun handle(e: Exception): T

    class Base(
        private val manageResources: ManageResources
    ): HandleError<String> {

        override fun handle(e: Exception): String = manageResources.string(
            when(e) {
                is NoInternetConnectionException -> R.string.no_connection_message
                else -> R.string.service_is_unavailable
            }
        )
    }
}