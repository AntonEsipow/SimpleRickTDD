package com.bigtoapp.simplericktesttdd.domain.character

import com.bigtoapp.simplericktesttdd.R
import com.bigtoapp.simplericktesttdd.domain.character.CharacterDomain
import com.bigtoapp.simplericktesttdd.presentation.CharacterDetailsUi

class CharacterDomainToUi : CharacterDomain.Mapper<CharacterDetailsUi> {

    override fun map(
        id: Int,
        name: String,
        status: String,
        imageUrl: String,
        species: String,
        gender: Int
    ) = CharacterDetailsUi(id.toString(), name, status, imageUrl, species, gender)

    override fun returnIcon(gender: String): Int {
        return when (gender.lowercase()) {
            "male" -> R.drawable.ic_male_24
            "female" -> R.drawable.ic_female_24
            else -> R.drawable.ic_unknown_24
        }
    }
}