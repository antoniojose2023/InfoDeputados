package br.com.antoniojoseuchoa.infodeputados.domain.proposicoes

data class Dado (
    val id: Long,
    val uri: String,
    val siglaTipo: String,
    val codTipo: Long,
    val numero: Long,
    val ano: Long,
    val ementa: String
)