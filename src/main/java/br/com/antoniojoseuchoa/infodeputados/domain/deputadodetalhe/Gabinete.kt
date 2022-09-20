package br.com.antoniojoseuchoa.infodeputados.domain.deputadodetalhe

import java.io.Serializable

data class Gabinete (
    val andar: String,
    val email: String,
    val nome: String,
    val predio: String,
    val sala: String,
    val telefone: String
):Serializable