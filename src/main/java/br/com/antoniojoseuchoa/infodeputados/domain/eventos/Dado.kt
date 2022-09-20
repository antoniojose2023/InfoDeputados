package br.com.antoniojoseuchoa.infodeputados.domain.eventos

data class Dado (
    val id: Long,
    val uri: String,
    val dataHoraInicio: String,
    val dataHoraFim: String? = null,
    val situacao: Situacao,
    val descricaoTipo: String,
    val descricao: String,
    val localExterno: Any? = null,
    val orgaos: List<Orgao>,
    val localCamara: LocalCamara,
    val urlRegistro: String? = null
)
