package com.bigtoapp.simplericktesttdd.details.domain

import com.bigtoapp.simplericktesttdd.details.presentation.CharacterDetailsUi

class DetailsUiMapper: CharacterDetailsDomain.Mapper<CharacterDetailsUi> {

    override fun map(
        id: String,
        name: String,
        status: String,
        species: String,
        gender: String
    ): CharacterDetailsUi =
        CharacterDetailsUi(id, name, status, species, gender)
}