package com.bigtoapp.simplericktesttdd.details.data

import com.bigtoapp.simplericktesttdd.details.data.cloud.CharacterDetailsCloudDataSource
import com.bigtoapp.simplericktesttdd.details.domain.CharacterDetailsDomain
import com.bigtoapp.simplericktesttdd.details.domain.CharacterDetailsRepository

class BaseCharactersRepository(
    private val cloudDataSource: CharacterDetailsCloudDataSource,
    private val handleDataRequest: HandleDataRequest
): CharacterDetailsRepository {

    override suspend fun fetchCharacterDetails(id: String) = handleDataRequest.handle {
            cloudDataSource.fetchCharacterDetails(id)
        }
}