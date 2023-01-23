package com.emanuelgalvao.buscacep.utils

import com.emanuelgalvao.buscacep.status.CepValidationStatus
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ValidatorTest {

   private lateinit var validator: Validator

   @Before
   fun setup() {
       validator = Validator.instance
   }

    @Test
    fun `when cep is empty, the not empty validation should return false`() {
        val cep = ""
        Assert.assertFalse(validator.isNotEmpty(cep))
    }

    @Test
    fun `when cep is not empty, the not empty validation should return true`() {
        val cep = "01001000"
        Assert.assertTrue(validator.isNotEmpty(cep))
    }

    @Test
    fun `when cep is null, the not null validation should return false`() {
        val cep = null
        Assert.assertFalse(validator.isNotNull(cep))
    }

    @Test
    fun `when cep is not null, the not null validation should return true`() {
        val cep = "01001000"
        Assert.assertTrue(validator.isNotNull(cep))
    }

    @Test
    fun `when cep lenght is different than 8, the length validation should return false`() {
        val cep = "0100100"
        Assert.assertFalse(validator.correctLength(cep))
    }

    @Test
    fun `when cep length is equals to 8, the length validation should return true`() {
        val cep = "01001000"
        Assert.assertTrue(validator.correctLength(cep))
    }

    @Test
    fun `when cep contains letters, the numbers validation should return false`() {
        val cep = "O10010A0"
        Assert.assertFalse(validator.containsOnlyNumbers(cep))
    }

    @Test
    fun `when cep contains symbols, the numbers validation should return false`() {
        val cep = "01001-00"
        Assert.assertFalse(validator.containsOnlyNumbers(cep))
    }

    @Test
    fun `when cep contains only numbers, the numbers validation should return true`() {
        val cep = "01001000"
        Assert.assertTrue(validator.containsOnlyNumbers(cep))
    }

    @Test
    fun `when cep is empty, the cep validation should return status empty`() {
        val cep = ""
        Assert.assertEquals(CepValidationStatus.CEP_EMPTY, validator.validateCep(cep))
    }

    @Test
    fun `when cep length is different than 8, the cep validation should return status incorrect length`() {
        val cep = "010010001"
        Assert.assertEquals(CepValidationStatus.CEP_INCORRECT_LENGTH, validator.validateCep(cep))
    }

    @Test
    fun `when cep contains letters, the cep validation should return status contains letters`() {
        val cep = "0100100A"
        Assert.assertEquals(CepValidationStatus.CEP_CONTAINS_LETTERS, validator.validateCep(cep))
    }

    @Test
    fun `when cep is valid, the cep validation should return status valid`() {
        val cep = "01001000"
        Assert.assertEquals(CepValidationStatus.CEP_VALID, validator.validateCep(cep))
    }

}