package com.bigtoapp.simplericktesttdd.presentation

import com.bigtoapp.simplericktesttdd.domain.character.CharacterDomain
import com.bigtoapp.simplericktesttdd.domain.character.CharacterDomainToUi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterResultMapperTest: BaseTest() {

    private lateinit var communications: TestCharacterDetailsCommunication
    private lateinit var mapper: CharacterResultMapper
    private lateinit var testDomainToUi: TestCharacterDomainToUi

    @Before
    fun setUp(){
        communications = TestCharacterDetailsCommunication()
        testDomainToUi = TestCharacterDomainToUi()
        mapper = CharacterResultMapper(communications, testDomainToUi)
    }

    @Test
    fun `test error`() {
        mapper.map(CharacterDomain(), "not empty message")
        assertEquals(UiState.ShowError("not empty message"), communications.stateCalledList[0])
    }

    @Test
    fun `test success with data`() {
        mapper.map(detailsDomain, "")

        assertEquals(UiState.Success, communications.stateCalledList[0])
        assertEquals(1, communications.timesShowDetails)
        assertEquals(detailsUi, communications.detailsUiList[0])
    }
}