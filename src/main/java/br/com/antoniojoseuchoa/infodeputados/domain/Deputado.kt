package br.com.antoniojoseuchoa.infodeputados.domain

import java.io.Serializable

data class Deputado (
    val id: Long,
    val uri: String,
    val nome: String,
    val siglaPartido: String,
    val uriPartido: String,
    val siglaUf: String,
    val idLegislatura: Long,
    val urlFoto: String,
    val email: Any? = null
): Serializable{}