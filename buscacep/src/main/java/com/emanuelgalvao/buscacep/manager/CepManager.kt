package com.emanuelgalvao.buscacep.manager

import com.emanuelgalvao.buscacep.callback.CepCallback
import com.emanuelgalvao.buscacep.data.CepRepository

class CepManager(private val cepRepository: CepRepository) {

    suspend fun getCepData(cep: String): CepCallback {
        return cepRepository.searchCep(cep)
    }
}