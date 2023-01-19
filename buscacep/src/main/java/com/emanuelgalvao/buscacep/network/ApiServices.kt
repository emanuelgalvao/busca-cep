package com.emanuelgalvao.buscacep.network


class ApiServices {

    companion object {

        val cepService = RetrofitClient.getClient().create(CepService::class.java)

    }
}