package com.emanuelgalvao.buscacep.data

import com.emanuelgalvao.buscacep.callback.CepApiCallback
import com.emanuelgalvao.buscacep.model.CepModel
import com.emanuelgalvao.buscacep.network.CepService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CepDataSource(val cepApiService: CepService): CepRepository {

    override fun searchCep(cep: String, callback: CepApiCallback) {
        cepApiService.searchCep(cep).enqueue(object: Callback<CepModel> {
            override fun onResponse(call: Call<CepModel>, response: Response<CepModel>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.success(it)
                    } ?: callback.error()
                } else {
                    callback.error()
                }
            }

            override fun onFailure(call: Call<CepModel>, t: Throwable) {
                callback.error()
            }

        })
    }
}