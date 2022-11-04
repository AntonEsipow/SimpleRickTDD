package com.bigtoapp.simplericktesttdd.domain

import com.bigtoapp.simplericktesttdd.domain.character.CharacterDomain

interface CharacterRepository {

    suspend fun fetchCharacterDetails(id: String): CharacterDomain
}