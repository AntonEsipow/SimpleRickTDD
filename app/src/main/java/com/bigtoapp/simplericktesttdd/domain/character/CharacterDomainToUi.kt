package com.bigtoapp.simplericktesttdd.domain.character

import com.bigtoapp.simplericktesttdd.domain.character.CharacterDomain
import com.bigtoapp.simplericktesttdd.presentation.CharacterDetailsUi

class CharacterDomainToUi: CharacterDomain.Mapper<CharacterDetailsUi> {

    override fun map(
        id: String,
        name: String,
        status: String,
        species: String,
        gender: String
    ): CharacterDetailsUi =
        CharacterDetailsUi(id, name, status, species, gender)
}