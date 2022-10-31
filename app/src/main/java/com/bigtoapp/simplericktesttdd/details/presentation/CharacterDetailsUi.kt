package com.bigtoapp.simplericktesttdd.details.presentation

import android.widget.TextView
import com.bigtoapp.simplericktesttdd.details.core.presentation.Mapper

data class CharacterDetailsUi(
    private val id: String,
    private val name: String,
    private val status: String,
    private val species: String,
    private val gender: String
): Mapper<Boolean, CharacterDetailsUi> {

    // todo check where used
    fun <T> map(mapper: Mapper<T>): T = mapper.map(name, status, species, gender)

    interface Mapper<T> {
        // todo think how to refactor
        fun map(name: String, status: String, species: String, gender: String): T
    }
    // todo check where used
    override fun map(source: CharacterDetailsUi) = source.id == id
}

class DetailsUi(
    private val nameTextView: TextView,
    private val statusTextView: TextView,
    private val speciesTextView: TextView,
    private val genderTextView: TextView
): CharacterDetailsUi.Mapper<Unit> {
    override fun map(name: String, status: String, species: String, gender: String) {
        nameTextView.text = name
        statusTextView.text = status
        speciesTextView.text = species
        genderTextView.text = gender
    }
}