package com.bigtoapp.simplericktesttdd.details.sl

import com.bigtoapp.simplericktesttdd.details.core.domain.HandleError
import com.bigtoapp.simplericktesttdd.details.core.sl.Module
import com.bigtoapp.simplericktesttdd.details.data.BaseCharactersRepository
import com.bigtoapp.simplericktesttdd.details.data.DetailsDataToDomain
import com.bigtoapp.simplericktesttdd.details.data.HandleDataError
import com.bigtoapp.simplericktesttdd.details.data.HandleDataRequest
import com.bigtoapp.simplericktesttdd.details.data.cloud.CharacterDetailsCloudDataSource
import com.bigtoapp.simplericktesttdd.details.data.cloud.CharactersService
import com.bigtoapp.simplericktesttdd.details.domain.CharacterDetailsInteractor
import com.bigtoapp.simplericktesttdd.details.domain.DetailsDomainToUi
import com.bigtoapp.simplericktesttdd.details.domain.HandleDomainRequest
import com.bigtoapp.simplericktesttdd.details.presentation.*
import com.bigtoapp.simplericktesttdd.main.sl.Core

class CharacterDetailsModule(private val core: Core): Module<CharacterDetailsViewModel.Base> {

    override fun viewModel(): CharacterDetailsViewModel.Base {

        val communications = CharacterDetailsCommunications.Base(
            ProgressCommunication.Base(),
            DetailsStateCommunication.Base(),
            ShowDetailsCommunication.Base()
        )

        return CharacterDetailsViewModel.Base(
            HandleCharacterRequest.Base(
                core.provideDispatchers(),
                communications,
                DetailsResultMapper(communications, DetailsDomainToUi())
            ),
            communications,
            CharacterDetailsInteractor.Base(
                BaseCharactersRepository(
                    CharacterDetailsCloudDataSource.Base(
                        core.service(CharactersService::class.java)
                    ),
                    HandleDataRequest.Base(
                        DetailsDataToDomain(),
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