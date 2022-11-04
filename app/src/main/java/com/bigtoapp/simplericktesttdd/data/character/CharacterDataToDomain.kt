package com.bigtoapp.simplericktesttdd.data.character

import com.bigtoapp.simplericktesttdd.domain.character.CharacterDomain

class CharacterDataToDomain: CharacterData.Mapper<CharacterDomain> {

    override fun map(
        created: String,
        episode: List<String>,
        gender: String,
        id: Int,
        image: String,
        location: CharacterData.Location,
        name: String,
        origin: CharacterData.Origin,
        species: String,
        status: String,
        type: String,
        url: String
    ): CharacterDomain = CharacterDomain(
        id.toString(), name, status, species, gender
    )
}