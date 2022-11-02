package com.bigtoapp.simplericktesttdd.details.data.cloud

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface CloudModule {

    fun <T> service(clazz: Class<T>): T

    class Debug: CloudModule {

        override fun <T> service(clazz: Class<T>): T {
            // todo make abstract values as in coreMVVM
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val interceptor = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            return retrofit.create(clazz)
        }

        companion object {
            private const val BASE_URL: String = "https://rickandmortyapi.com/api/"
        }
    }
}