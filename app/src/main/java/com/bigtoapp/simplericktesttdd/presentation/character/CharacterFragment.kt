package com.bigtoapp.simplericktesttdd.presentation.character

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bigtoapp.simplericktesttdd.R
import com.bigtoapp.simplericktesttdd.presentation.CharacterUi
import com.bigtoapp.simplericktesttdd.presentation.main.BaseFragment

class CharacterFragment: BaseFragment<CharacterViewModel.Base>() {

    override val layoutId = R.layout.fragment_character
    override val viewModelClass = CharacterViewModel.Base::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val nameTextView = view.findViewById<TextView>(R.id.nameTextView)
        val statusTextView = view.findViewById<TextView>(R.id.statusTextView)
        val characterImageView = view.findViewById<ImageView>(R.id.characterImageView)
        val speciesTextView = view.findViewById<TextView>(R.id.speciesTextView)
        val genderImageView = view.findViewById<ImageView>(R.id.genderImageView)

        val mapper = CharacterUi(nameTextView, statusTextView, characterImageView, speciesTextView, genderImageView)

        // todo get id from list fragment
        // hardcode for test
        viewModel.fetchCharacterDetails("42")

        viewModel.observeState(this) {
            it.apply(nameTextView)
        }

        viewModel.observeDetails(this) {
            it.map(mapper)
        }

        viewModel.observeProgress(this) {
            progressBar.visibility = it
        }
    }
}