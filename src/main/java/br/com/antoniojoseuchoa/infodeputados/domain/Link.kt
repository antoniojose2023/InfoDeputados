package br.com.antoniojoseuchoa.infodeputados.domain

import java.io.Serializable

data class Link (
    val rel: String,
    val href: String
): Serializable{}
