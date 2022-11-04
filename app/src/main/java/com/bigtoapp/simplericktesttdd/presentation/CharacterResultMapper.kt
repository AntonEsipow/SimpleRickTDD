package com.bigtoapp.simplericktesttdd.presentation

import com.bigtoapp.simplericktesttdd.domain.character.CharacterDomain
import com.bigtoapp.simplericktesttdd.domain.CharacterResult

class CharacterResultMapper(
    private val communications: CharacterDetailsCommunications,
    private val mapper: CharacterDomain.Mapper<CharacterDetailsUi>
): CharacterResult.Mapper<Unit> {

    override fun map(details: CharacterDomain, errorMessage: String) = communications.showState(
        if(errorMessage.isEmpty()) {
            communications.showDetails(details.map(mapper))
            UiState.Success
        } else
            UiState.ShowError(errorMessage)
    )
}