package com.bigtoapp.simplericktesttdd.details.data.cloud

import com.bigtoapp.simplericktesttdd.details.data.CharacterDetailsData

interface CharacterDetailsCloudDataSource {

    suspend fun fetchCharacterDetails(id: String) : CharacterDetailsData
}