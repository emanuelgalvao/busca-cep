package com.emanuelgalvao.buscacep.utils

import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class CepHandlerTest {

    private lateinit var cepHandler: CepHandler

    @Before
    fun setup() {
        cepHandler = CepHandler.instance
    }

    @Test
    fun `when cep constains dashs, the function remove dashs should return cep without dashs`() {
        val cep = "01001-000"
        Assert.assertEquals("01001000", cepHandler.removeDashs(cep))
    }

    @Test
    fun `when cep constains spaces, the function remove spaces should return cep without spaces`() {
        val cep = "01001 000"
        Assert.assertEquals("01001000", cepHandler.removeSpaces(cep))
    }

    @Test
    fun `when cep constains dots, the function remove dots should return cep without dots`() {
        val cep = "01.001.000"
        Assert.assertEquals("01001000", cepHandler.removeDots(cep))
    }

    @Test
    fun `when cep is in incorrect format, the format cep function should return cep in correct format`() {
        val cep = "01.001-000 "
        Assert.assertEquals("01001000", cepHandler.formatCepToCorrectFormat(cep))
    }
}