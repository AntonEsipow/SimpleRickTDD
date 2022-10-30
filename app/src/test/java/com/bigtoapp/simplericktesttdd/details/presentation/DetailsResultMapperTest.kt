package com.bigtoapp.simplericktesttdd.details.presentation

import com.bigtoapp.simplericktesttdd.details.domain.CharacterDetailsDomain
import com.bigtoapp.simplericktesttdd.details.domain.DetailsUiMapper
import junit.framework.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailsResultMapperTest: BaseTest() {

    private lateinit var communications: TestCharacterDetailsCommunication
    private lateinit var mapper: DetailsResultMapper

    @Before
    fun setUp(){
        communications = TestCharacterDetailsCommunication()
        mapper = DetailsResultMapper(communications, DetailsUiMapper())
    }

    @Test
    fun `test error`() {
        mapper.map(CharacterDetailsDomain(), "not empty message")
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