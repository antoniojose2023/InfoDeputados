package br.com.antoniojoseuchoa.infodeputados.domain.eventos

data class Evento (
    val dados: List<Dado>,
    val links: List<Link>
)