package com.bigtoapp.simplericktesttdd.details.presentation

import android.view.View
import com.bigtoapp.simplericktesttdd.details.core.presentation.DispatchersList
import com.bigtoapp.simplericktesttdd.details.domain.DetailsResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface HandleCharacterRequest {

    fun handle(
        coroutineScope: CoroutineScope,
        block: suspend () -> DetailsResult
    )

    class Base(
        private val dispatcher: DispatchersList,
        private val communications: CharacterDetailsCommunications,
        private val detailsResultMapper: DetailsResult.Mapper<Unit>
    ): HandleCharacterRequest {

        override fun handle(coroutineScope: CoroutineScope, block: suspend () -> DetailsResult) {

            communications.showProgress(View.VISIBLE)
            coroutineScope.launch(dispatcher.io()) {
                val result = block.invoke()
                communications.showProgress(View.GONE)
                result.map(detailsResultMapper)
            }
        }
    }
}