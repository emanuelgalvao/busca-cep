package com.emanuelgalvao.buscacep.data

import com.emanuelgalvao.buscacep.callback.CepCallback

interface CepRepository {

    suspend fun searchCep(cep: String): CepCallback
}