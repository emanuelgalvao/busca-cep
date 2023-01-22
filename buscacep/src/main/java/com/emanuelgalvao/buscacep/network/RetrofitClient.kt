package com.emanuelgalvao.buscacep.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {

        private const val BASE_URL = "https://viacep.com.br/ws/"

        private lateinit var client: Retrofit

        fun getClient(): Retrofit {
            if (!this::client.isInitialized)
                initializeClient()
            return client
        }

        private fun initializeClient() {
            client = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }
    }
}