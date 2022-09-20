package br.com.antoniojoseuchoa.infodeputados.domain.deputadodetalhe

import java.io.Serializable

data class DeputadoDetalhe (
    val dados: Dados,
    val links: List<Link2>
):Serializable{}