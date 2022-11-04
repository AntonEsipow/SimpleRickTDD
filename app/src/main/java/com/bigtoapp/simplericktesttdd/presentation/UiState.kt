package com.bigtoapp.simplericktesttdd.presentation

import android.widget.TextView

sealed class UiState {

    abstract fun apply(textView: TextView)

    object Success: UiState() {
        override fun apply(textView: TextView) = Unit
    }

    abstract class AbstractError(
        private val message: String
    ): UiState() {
        override fun apply(textView: TextView) {
            textView.text = message
        }
    }

    data class ShowError(private val message: String): AbstractError(message)
}