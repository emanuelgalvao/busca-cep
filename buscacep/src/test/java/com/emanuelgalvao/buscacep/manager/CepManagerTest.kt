package com.emanuelgalvao.buscacep.manager

import com.emanuelgalvao.buscacep.callback.CepCallback
import com.emanuelgalvao.buscacep.data.CepRepository
import com.emanuelgalvao.buscacep.model.CepModel
import com.emanuelgalvao.buscacep.status.CepErrorStatus
import com.emanuelgalvao.buscacep.status.CepValidationStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
internal class CepManagerTest {

    private lateinit var cepManager: CepManager

    @Mock
    private lateinit var cepRepository: CepRepository

    @Before
    fun setup() {
        cepManager = CepManager(cepRepository)
    }

    @Test
    fun `when cep is valid, the callback should return success`() = runTest {

        val cep = "01001000"
        val cepModel = CepModel(
            bairro = "Sé",
            cep = "01001-000",
            complemento = "lado ímpar",
            ddd = "11",
            gia= "1004",
            ibge = "3550308",
            localidade = "São Paulo",
            logradouro = "Praça da Sé",
            siafi = "7107",
            uf = "SP"
        )
        whenever(cepRepository.searchCep(cep))
            .thenReturn(CepCallback.Success(cepModel))

        val callback = cepManager.getCepData(cep)

        Assert.assertTrue(callback is CepCallback.Success)
    }

    @Test
    fun `when cep is valid, but an error occurred on the server, the callback should return error with status server error`() = runTest {

        val cep = "01001000"

        whenever(cepRepository.searchCep(cep))
            .thenReturn(CepCallback.Error(CepErrorStatus.SERVER_ERROR))

        val callback = cepManager.getCepData(cep)
        val errorStatus = (callback as? CepCallback.Error)?.cepErrorStatus

        Assert.assertTrue(errorStatus == CepErrorStatus.SERVER_ERROR)
    }

    @Test
    fun `when cep is valid, but not exist, the callback should return error with status invalid cep`() = runTest {

        val cep = "99999999"

        whenever(cepRepository.searchCep(cep))
            .thenReturn(CepCallback.Error(CepErrorStatus.INVALID_CEP))

        val callback = cepManager.getCepData(cep)
        val errorStatus = (callback as? CepCallback.Error)?.cepErrorStatus

        Assert.assertTrue(errorStatus == CepErrorStatus.INVALID_CEP)
    }

    @Test
    fun `when cep is empty, the callback should return validation with status empty`() = runTest {

        val cep = ""
        whenever(cepRepository.searchCep(cep))
            .thenReturn(CepCallback.Validation(CepValidationStatus.CEP_EMPTY))

        val callback = cepManager.getCepData(cep)
        val validationStatus = (callback as? CepCallback.Validation)?.cepValidationStatus

        Assert.assertTrue(validationStatus == CepValidationStatus.CEP_EMPTY)
    }

    @Test
    fun `when cep contains letters, the callback should return validation with status contains letters`() = runTest {

        val cep = "O10010A0"
        whenever(cepRepository.searchCep(cep))
            .thenReturn(CepCallback.Validation(CepValidationStatus.CEP_CONTAINS_LETTERS))

        val callback = cepManager.getCepData(cep)
        val validationStatus = (callback as? CepCallback.Validation)?.cepValidationStatus

        Assert.assertTrue(validationStatus == CepValidationStatus.CEP_CONTAINS_LETTERS)
    }

    @Test
    fun `when cep lenght is different than 8, the callback should return validation with status incorrect length`() = runTest {

        val cep = "O10010000"
        whenever(cepRepository.searchCep(cep))
            .thenReturn(CepCallback.Validation(CepValidationStatus.CEP_INCORRECT_LENGTH))

        val callback = cepManager.getCepData(cep)
        val validationStatus = (callback as? CepCallback.Validation)?.cepValidationStatus

        Assert.assertTrue(validationStatus == CepValidationStatus.CEP_INCORRECT_LENGTH)
    }
}