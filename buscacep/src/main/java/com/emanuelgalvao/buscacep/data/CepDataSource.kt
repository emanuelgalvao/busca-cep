package com.emanuelgalvao.buscacep.data

import com.emanuelgalvao.buscacep.callback.CepCallback
import com.emanuelgalvao.buscacep.model.CepModel
import com.emanuelgalvao.buscacep.network.CepService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CepDataSource(val cepApiService: CepService): CepRepository {

    override suspend fun searchCep(cep: String): CepCallback {

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