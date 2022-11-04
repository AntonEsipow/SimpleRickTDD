package com.bigtoapp.simplericktesttdd.data

import com.bigtoapp.simplericktesttdd.data.cloud.CharacterCloudDataSource
import com.bigtoapp.simplericktesttdd.domain.CharacterRepository

class BaseCharactersRepository(
    private val cloudDataSource: CharacterCloudDataSource,
    private val handleDataRequest: HandleDataRequest
): CharacterRepository {

    override suspend fun fetchCharacterDetails(id: String) = handleDataRequest.handle {
            cloudDataSource.fetchCharacterDetails(id)
        }
}