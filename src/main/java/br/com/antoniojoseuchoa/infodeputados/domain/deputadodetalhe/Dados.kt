package br.com.antoniojoseuchoa.infodeputados.domain.deputadodetalhe

import java.io.Serializable

data class Dados (
    val cpf: String,
    val dataFalecimento: String,
    val dataNascimento: String,
    val escolaridade: String,
    val id: Long,
    val municipioNascimento: String,
    val nomeCivil: String,
    val redeSocial: List<String>,
    val sexo: String,
    val ufNascimento: String,
    val ultimoStatus: UltimoStatus,
    val uri: String,
    val urlWebsite: String
):Serializable{}