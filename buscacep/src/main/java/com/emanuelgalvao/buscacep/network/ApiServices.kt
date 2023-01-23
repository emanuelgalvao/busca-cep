package com.emanuelgalvao.buscacep.network


internal class ApiServices {

    companion object {

        val cepService: CepService = RetrofitClient.getClient().create(CepService::class.java)

    }
}