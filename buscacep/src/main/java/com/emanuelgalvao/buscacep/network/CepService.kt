package com.emanuelgalvao.buscacep.network

import com.emanuelgalvao.buscacep.model.CepModel
import com.emanuelgalvao.buscacep.model.CepResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CepService {

    @GET("{cep}/json/")
    suspend fun searchCep(@Path(value = "cep") cep: String) : Response<CepResponse>
}