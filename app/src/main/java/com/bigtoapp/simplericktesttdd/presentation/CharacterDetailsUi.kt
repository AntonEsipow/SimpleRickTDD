package com.bigtoapp.simplericktesttdd.presentation

import android.widget.ImageView
import android.widget.TextView
import com.bigtoapp.simplericktesttdd.core.presentation.Mapper
import com.squareup.picasso.Picasso

data class CharacterDetailsUi(
    private val id: String,
    private val name: String,
    private val status: String,
    private val imageUrl: String,
    private val species: String,
    private val gender: Int
) {

    // todo check where used
    fun <T> map(mapper: Mapper<T>): T = mapper.map(name, status, imageUrl, species, gender)

    interface Mapper<T> {
        // todo think how to refactor
        fun map(name: String, status: String, imageUrl: String, species: String, gender: Int): T
    }
}

class CharacterUi(
    private val nameTextView: TextView,
    private val statusTextView: TextView,
    private val characterImageView: ImageView,
    private val speciesTextView: TextView,
    private val genderImageView: ImageView
): CharacterDetailsUi.Mapper<Unit> {

    override fun map(name: String, status: String, imageUrl: String, species: String, gender: Int) {
        nameTextView.text = name
        statusTextView.text = status
        Picasso.get().load(imageUrl).into(characterImageView)
        speciesTextView.text = species
        genderImageView.setImageResource(gender)
    }
}