package com.bigtoapp.simplericktesttdd.details.data

import com.bigtoapp.simplericktesttdd.details.core.domain.HandleError
import com.bigtoapp.simplericktesttdd.details.core.domain.NoInternetConnectionException
import com.bigtoapp.simplericktesttdd.details.core.domain.ServiceUnavailableException
import java.net.UnknownHostException

class HandleDataError: HandleError<Exception> {

    override fun handle(e: Exception): Exception = when(e) {
        is UnknownHostException -> NoInternetConnectionException()
        else -> ServiceUnavailableException()
    }
}