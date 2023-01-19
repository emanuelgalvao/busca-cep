package com.emanuelgalvao.buscacep.data

import com.emanuelgalvao.buscacep.callback.CepApiCallback

interface CepRepository {

    fun searchCep(cep: String, callback: CepApiCallback)
}