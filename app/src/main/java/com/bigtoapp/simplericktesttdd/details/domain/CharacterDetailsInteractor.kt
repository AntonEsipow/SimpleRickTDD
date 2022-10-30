package com.bigtoapp.simplericktesttdd.details.domain

import com.bigtoapp.simplericktesttdd.details.presentation.DetailsResult

interface CharacterDetailsInteractor {

    suspend fun fetchCharacterDetails(id: String): DetailsResult
}