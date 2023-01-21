package com.emanuelgalvao.buscacep.network

import com.emanuelgalvao.buscacep.model.CepModel
import retrofit2.Call
import retrofit2.Response

interface CepService {

    suspend fun searchCep(cep: String) : Response<CepModel>
}