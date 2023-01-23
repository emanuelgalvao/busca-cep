package com.emanuelgalvao.buscacep

import com.emanuelgalvao.buscacep.callback.CepCallback
import com.emanuelgalvao.buscacep.data.CepDataSource
import com.emanuelgalvao.buscacep.data.CepRepository
import com.emanuelgalvao.buscacep.manager.CepManager
import com.emanuelgalvao.buscacep.network.ApiServices

class SearchCep private constructor() {

    private val cepRepository: CepRepository by lazy {
        CepDataSource(ApiServices.cepService)
    }

    private val cepManager: CepManager by lazy {
        CepManager(cepRepository)
    }
    companion object {
        val instance: SearchCep by lazy {
            SearchCep()
        }
    }

    suspend fun getCepData(cep: String): CepCallback {
        return cepManager.getCepData(cep)
    }
}