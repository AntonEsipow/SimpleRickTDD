package com.bigtoapp.simplericktesttdd.data.cloud

import com.bigtoapp.simplericktesttdd.data.character.cloud.MockCharacterService
import com.bigtoapp.simplericktesttdd.data.character.cloud.MockCharacterResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface CloudModule {

    fun <T> service(clazz: Class<T>): T

    class Mock: CloudModule {
        override fun <T> service(clazz: Class<T>): T = MockCharacterService(MockCharacterResponse()) as T
    }

    abstract class Abstract(
        private val loggingLevel: HttpLoggingInterceptor.Level
    ): CloudModule {

        override fun <T> service(clazz: Class<T>): T {
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val interceptor = HttpLoggingInterceptor().apply {
                setLevel(loggingLevel)
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

    class Debug: Abstract(HttpLoggingInterceptor.Level.BODY)
    class Release: Abstract(HttpLoggingInterceptor.Level.NONE)
}