package com.bigtoapp.simplericktesttdd.details.data

import com.bigtoapp.simplericktesttdd.details.domain.CharacterDetailsDomain

class DetailsDataToDomain: CharacterDetailsData.Mapper<CharacterDetailsDomain> {

    override fun map(
        created: String,
        episode: List<String>,
        gender: String,
        id: Int,
        image: String,
        location: CharacterDetailsData.Location,
        name: String,
        origin: CharacterDetailsData.Origin,
        species: String,
        status: String,
        type: String,
        url: String
    ): CharacterDetailsDomain = CharacterDetailsDomain(
        id.toString(), name, status, species, gender
    )
}