package com.bigtoapp.simplericktesttdd.core.data

import retrofit2.Response


interface MockResponse<T> {
    fun makeResponse(body: T): Response<T>
}