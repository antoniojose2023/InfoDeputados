package br.com.antoniojoseuchoa.infodeputados.domain.despesas

data class Dado (
    val ano: Long,
    val mes: Long,
    val tipoDespesa: String,
    val codDocumento: Long,
    val tipoDocumento: TipoDocumento,
    val codTipoDocumento: Long,
    val dataDocumento: String,
    val numDocumento: String,
    val valorDocumento: Double,
    val urlDocumento: String,
    val nomeFornecedor: String,
    val cnpjCpfFornecedor: String,
    val valorLiquido: Double,
    val valorGlosa: Long,
    val numRessarcimento: String,
    val codLote: Long,
    val parcela: Long
)
