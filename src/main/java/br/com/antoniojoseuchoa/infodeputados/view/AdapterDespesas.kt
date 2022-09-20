package br.com.antoniojoseuchoa.infodeputados.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.antoniojoseuchoa.infodeputados.databinding.ItemDespesasBinding
import br.com.antoniojoseuchoa.infodeputados.domain.despesas.Dado

class AdapterDespesas(val context: Context, var list: List<Dado>): RecyclerView.Adapter<AdapterDespesas.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemDespesasBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind( item )
    }

    override fun getItemCount() = list.size

    open class ViewHolder(val binding: ItemDespesasBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Dado) {
                binding.tvAno.text = "ANO: ${item.ano}"

                when(item.mes.toInt()){
                      1 -> { binding.tvMes.text = "MES: Janeiro"}
                      2 -> { binding.tvMes.text = "MES: Fevereiro"}
                      3 -> { binding.tvMes.text = "MES: Março" }
                      4 -> { binding.tvMes.text = "MES: Abril" }
                      5 -> { binding.tvMes.text = "MES: Maio" }
                      6 -> { binding.tvMes.text = "MES: Junho" }
                      7 -> { binding.tvMes.text = "MES: Julho" }
                      8 -> { binding.tvMes.text = "MES: Agosto" }
                      9 -> { binding.tvMes.text = "MES: Setembro"}
                      10 -> { binding.tvMes.text = "MES: Outubro" }
                      11 -> { binding.tvMes.text = "MES: Novembro" }
                      12 -> { binding.tvMes.text = "MES: Dezembro" }
                }

                binding.tvTipoDespesa.text = "TIPO DESPESAS: ${item.tipoDespesa}"
                binding.tvValor.text = "VALOR R$: ${item.valorLiquido}"
        }
    }

}
