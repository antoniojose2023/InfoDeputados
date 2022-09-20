package br.com.antoniojoseuchoa.infodeputados.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.antoniojoseuchoa.infodeputados.databinding.ItemProposicoesBinding
import br.com.antoniojoseuchoa.infodeputados.domain.proposicoes.Dado

class AdapterProposicoes(val context: Context, val list: List<Dado>): RecyclerView.Adapter<AdapterProposicoes.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemProposicoesBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount() = list.size

    open class ViewHolder(val binding: ItemProposicoesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Dado) {
            binding.tvNumeroProposicoes.text = "Número: ${item.numero}"
            binding.tvAnoProposicoes.text = "Ano: ${item.ano}"
            binding.tvEmentaProposicoes.text = "Ementa : ${item.ementa}"
        }
    }

}