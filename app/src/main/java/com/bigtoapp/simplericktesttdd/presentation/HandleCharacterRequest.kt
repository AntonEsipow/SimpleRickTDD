package com.bigtoapp.simplericktesttdd.presentation

import android.view.View
import com.bigtoapp.simplericktesttdd.core.presentation.DispatchersList
import com.bigtoapp.simplericktesttdd.domain.CharacterResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface HandleCharacterRequest {

    fun handle(
        coroutineScope: CoroutineScope,
        block: suspend () -> CharacterResult
    )

    class Base(
        private val dispatcher: DispatchersList,
        private val communications: CharacterDetailsCommunications,
        private val characterResultMapper: CharacterResult.Mapper<Unit>
    ): HandleCharacterRequest {

        override fun handle(coroutineScope: CoroutineScope, block: suspend () -> CharacterResult) {

            communications.showProgress(View.VISIBLE)
            coroutineScope.launch(dispatcher.io()) {
                val result = block.invoke()
                communications.showProgress(View.GONE)
                result.map(characterResultMapper)
            }
        }
    }
}