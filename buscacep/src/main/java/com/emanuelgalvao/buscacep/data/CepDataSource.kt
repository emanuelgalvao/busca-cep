package com.emanuelgalvao.buscacep.data

import com.emanuelgalvao.buscacep.callback.CepCallback
import com.emanuelgalvao.buscacep.network.CepService
import com.emanuelgalvao.buscacep.status.ValidationStatus
import com.emanuelgalvao.buscacep.utils.Validator

class CepDataSource(val cepApiService: CepService): CepRepository {

    override suspend fun searchCep(cep: String): CepCallback {

        val validationStatus = Validator.instance.validateCep(cep)

        return if (validationStatus == ValidationStatus.CEP_VALID) {
            doSearchRequest(cep)
        } else {
            CepCallback.Validation(validationStatus)
        }
    }

    private suspend fun doSearchRequest(cep: String): CepCallback {

        val apiResponse = cepApiService.searchCep(cep)

        return if (apiResponse.isSuccessful) {
            apiResponse.body()?.let {
                CepCallback.Success(it)
            } ?: CepCallback.Error()
        } else {
            CepCallback.Error()
        }
    }
}