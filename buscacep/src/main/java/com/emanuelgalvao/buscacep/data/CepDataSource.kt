package com.emanuelgalvao.buscacep.data

import com.emanuelgalvao.buscacep.callback.CepCallback
import com.emanuelgalvao.buscacep.model.CepModel
import com.emanuelgalvao.buscacep.network.CepService
import com.emanuelgalvao.buscacep.status.CepErrorStatus
import com.emanuelgalvao.buscacep.status.CepValidationStatus
import com.emanuelgalvao.buscacep.utils.CepHandler
import com.emanuelgalvao.buscacep.utils.Validator

internal class CepDataSource(private val cepApiService: CepService): CepRepository {

    override suspend fun searchCep(cep: String): CepCallback {

        val cepFormatted = CepHandler.instance.formatCepToCorrectFormat(cep)

        val validationStatus = Validator.instance.validateCep(cepFormatted)

        return if (validationStatus == CepValidationStatus.CEP_VALID) {
            doSearchRequest(cepFormatted)
        } else {
            CepCallback.Validation(validationStatus)
        }
    }

    private suspend fun doSearchRequest(cep: String): CepCallback {

        val apiResponse = cepApiService.searchCep(cep)

        return if (apiResponse.isSuccessful) {
            apiResponse.body()?.let { cepResponse ->
                if (cepResponse.erro) {
                    CepCallback.Error(CepErrorStatus.INVALID_CEP)
                } else {
                    val cepModel = CepModel(
                        bairro = cepResponse.bairro,
                        cep = cepResponse.cep,
                        complemento = cepResponse.complemento,
                        ddd = cepResponse.ddd,
                        gia = cepResponse.gia,
                        ibge = cepResponse.ibge,
                        localidade = cepResponse.localidade,
                        logradouro = cepResponse.logradouro,
                        siafi = cepResponse.siafi,
                        uf = cepResponse.uf
                    )
                    CepCallback.Success(cepModel)
                }
            } ?: CepCallback.Error(CepErrorStatus.SERVER_ERROR)
        } else {
            CepCallback.Error(CepErrorStatus.SERVER_ERROR)
        }
    }
}