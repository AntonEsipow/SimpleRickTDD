package com.bigtoapp.simplericktesttdd.details.presentation

import com.bigtoapp.simplericktesttdd.details.domain.CharacterDetailsDomain

class DetailsResultMapper(
    private val communications: CharacterDetailsCommunications,
    private val mapper: CharacterDetailsDomain.Mapper<CharacterDetailsUi>
): DetailsResult.Mapper<Unit> {

    override fun map(details: CharacterDetailsDomain, errorMessage: String) = communications.showState(
        if(errorMessage.isEmpty()) {
            communications.showDetails(details.map(mapper))
            UiState.Success
        } else
            UiState.ShowError(errorMessage)
    )
}