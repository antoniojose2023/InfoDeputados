package br.com.antoniojoseuchoa.infodeputados.view

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
        fun bind(item: Dado) {
                binding.tvAno.text = "ANO: ${item.ano}"
                binding.tvMes.text = "MES: ${item.mes}"
                binding.tvTipoDespesa.text = "TIPO DESPESAS: ${item.tipoDespesa}"
                binding.tvValor.text = "VALOR R$: ${item.valorLiquido}"
        }
    }


}
