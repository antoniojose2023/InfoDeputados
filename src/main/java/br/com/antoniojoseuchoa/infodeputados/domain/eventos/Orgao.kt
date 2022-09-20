package br.com.antoniojoseuchoa.infodeputados.domain.eventos

data class Orgao (
    val id: Long,
    val uri: String,
    val sigla: String,
    val nome: String,
    val apelido: String,
    val codTipoOrgao: Long,
    val tipoOrgao: String,
    val nomePublicacao: String,
    val nomeResumido: String? = null
)