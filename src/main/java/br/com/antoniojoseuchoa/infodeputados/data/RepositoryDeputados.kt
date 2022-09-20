package br.com.antoniojoseuchoa.infodeputados.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RepositoryDeputados {

    companion object{
        private val url_base = "https://dadosabertos.camara.leg.br/"

        val retrofit = Retrofit.Builder()
            .baseUrl(url_base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create( ServicoApi::class.java )

    }
}