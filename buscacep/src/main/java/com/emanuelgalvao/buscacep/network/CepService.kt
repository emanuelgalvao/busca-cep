package com.emanuelgalvao.buscacep.network

import com.emanuelgalvao.buscacep.model.CepModel
import retrofit2.Call

interface CepService {

    fun searchCep(cep: String) : Call<CepModel>
}