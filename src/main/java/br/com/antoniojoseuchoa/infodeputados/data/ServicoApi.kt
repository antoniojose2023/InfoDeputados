package br.com.antoniojoseuchoa.infodeputados.data

import br.com.antoniojoseuchoa.infodeputados.domain.Dado
import br.com.antoniojoseuchoa.infodeputados.domain.deputadodetalhe.DeputadoDetalhe
import br.com.antoniojoseuchoa.infodeputados.domain.despesas.Despesas
import br.com.antoniojoseuchoa.infodeputados.domain.eventos.Evento

import br.com.antoniojoseuchoa.infodeputados.domain.proposicoes.Proposicoes
import br.com.antoniojoseuchoa.infodeputados.domain.votacao.DadoVotacao


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServicoApi {

    @GET("api/v2/deputados")
    fun getListaDeputados(): Call<Dado>

    @GET("api/v2/deputados/{id}")
    fun getDetalhesDeputado(@Path("id") id: Long): Call<DeputadoDetalhe>

    @GET("api/v2/eventos")
    fun getListEvento(): Call<Evento>

    @GET("api/v2/votacoes")
    fun getListVotacao(): Call<DadoVotacao>

    @GET("api/v2/proposicoes")
    fun getListProposicoes(): Call<Proposicoes>

    @GET("api/v2/deputados/{id}/despesas?ordem=DESC&ordenarPor=mes")
    fun getListDespesas(@Path("id") id: Int): Call<Despesas>


}