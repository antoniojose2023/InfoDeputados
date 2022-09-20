package br.com.antoniojoseuchoa.infodeputados.domain.eventos

data class LocalCamara (
    val nome: String,
    val predio: Any? = null,
    val sala: Any? = null,
    val andar: Any? = null
)
