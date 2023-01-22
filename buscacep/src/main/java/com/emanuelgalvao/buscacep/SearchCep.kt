package com.emanuelgalvao.buscacep

import com.emanuelgalvao.buscacep.data.CepDataSource
import com.emanuelgalvao.buscacep.data.CepRepository
import com.emanuelgalvao.buscacep.manager.CepManager
import com.emanuelgalvao.buscacep.network.ApiServices

class SearchCep private constructor() {

    companion object {

        private val cepRepository: CepRepository = CepDataSource(ApiServices.cepService)

        val instance: CepManager by lazy {
            CepManager(cepRepository)
        }
    }
}