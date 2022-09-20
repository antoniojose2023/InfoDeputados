package br.com.antoniojoseuchoa.infodeputados.domain.votacao

data class Dado (
    val id: String,
    val uri: String,
    val data: String,
    val dataHoraRegistro: String,
    val siglaOrgao: SiglaOrgao,
    val uriOrgao: String,
    val uriEvento: String? = null,
    val proposicaoObjeto: String? = null,
    val uriProposicaoObjeto: String? = null,
    val descricao: String,
    val aprovacao: Long? = null
)