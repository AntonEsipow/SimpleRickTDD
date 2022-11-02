package com.bigtoapp.simplericktesttdd.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.bigtoapp.simplericktesttdd.R
import com.bigtoapp.simplericktesttdd.details.core.sl.ProvideViewModel
import com.bigtoapp.simplericktesttdd.details.presentation.CharacterDetailsViewModel
import com.bigtoapp.simplericktesttdd.details.presentation.DetailsUi

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = (application as ProvideViewModel).provideViewModel(
            CharacterDetailsViewModel.Base::class.java, this
        )

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val statusTextView = findViewById<TextView>(R.id.statusTextView)
        val speciesTextView = findViewById<TextView>(R.id.speciesTextView)
        val genderTextView = findViewById<TextView>(R.id.genderTextView)

        val mapper = DetailsUi(nameTextView, statusTextView, speciesTextView, genderTextView)

        // todo get id from list fragment
        // hardcode for test
        viewModel.fetchCharacterDetails("97")

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