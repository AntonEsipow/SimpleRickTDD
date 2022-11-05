package com.bigtoapp.simplericktesttdd.sl.modules

import com.bigtoapp.simplericktesttdd.core.domain.HandleError
import com.bigtoapp.simplericktesttdd.core.sl.Module
import com.bigtoapp.simplericktesttdd.data.BaseCharactersRepository
import com.bigtoapp.simplericktesttdd.data.character.CharacterDataToDomain
import com.bigtoapp.simplericktesttdd.data.HandleDataError
import com.bigtoapp.simplericktesttdd.data.HandleDataRequest
import com.bigtoapp.simplericktesttdd.data.cloud.CharacterCloudDataSource
import com.bigtoapp.simplericktesttdd.data.character.cloud.CharacterService
import com.bigtoapp.simplericktesttdd.domain.CharacterInteractor
import com.bigtoapp.simplericktesttdd.domain.character.CharacterDomainToUi
import com.bigtoapp.simplericktesttdd.domain.HandleDomainRequest
import com.bigtoapp.simplericktesttdd.presentation.*
import com.bigtoapp.simplericktesttdd.presentation.character.CharacterViewModel
import com.bigtoapp.simplericktesttdd.sl.Core

class CharacterModule(private val core: Core): Module<CharacterViewModel.Base> {

    override fun viewModel(): CharacterViewModel.Base {

        val communications = CharacterDetailsCommunications.Base(
            ProgressCommunication.Base(),
            DetailsStateCommunication.Base(),
            ShowDetailsCommunication.Base()
        )

        return CharacterViewModel.Base(
            HandleCharacterRequest.Base(
                core.provideDispatchers(),
                communications,
                CharacterResultMapper(communications, CharacterDomainToUi())
            ),
            communications,
            CharacterInteractor.Base(
                BaseCharactersRepository(
                    CharacterCloudDataSource.Base(
                        core.service(CharacterService::class.java)
                    ),
                    HandleDataRequest.Base(
                        CharacterDataToDomain(),
                        HandleDataError()
                    )
                ),
                HandleDomainRequest.Base(
                    HandleError.Base(
                        core
                    )
                )
            )
        )
    }
}