package com.bigtoapp.simplericktesttdd

import org.junit.Test

class CharacterDetailsTest: BaseTest() {

    @Test
    fun test_check_character_details(){
        CharacterPage().run {
            name.checkText("Morty Smith")
            status.checkText("Alive")
            species.checkText("Human")
            gender.checkDrawable(R.drawable.ic_male_24)
        }
    }
}