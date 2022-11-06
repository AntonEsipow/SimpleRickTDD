package com.bigtoapp.simplericktesttdd.data.character.cloud

import com.bigtoapp.simplericktesttdd.core.data.MockResponse
import com.bigtoapp.simplericktesttdd.data.character.CharacterData
import okhttp3.Headers.Companion.toHeaders
import retrofit2.Response

class MockCharacterResponse: MockResponse<CharacterData> {

    override fun makeResponse(body: CharacterData): Response<CharacterData> =
        Response.success(body)
}