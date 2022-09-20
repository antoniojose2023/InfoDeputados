package br.com.antoniojoseuchoa.infodeputados.view

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.antoniojoseuchoa.infodeputados.R
import br.com.antoniojoseuchoa.infodeputados.databinding.ItemVotacaoBinding
import br.com.antoniojoseuchoa.infodeputados.domain.votacao.Dado

class AdapterVotacao(val context: Context, val list: List<Dado>): RecyclerView.Adapter<AdapterVotacao.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemVotacaoBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount() = list.size

    open class ViewHolder(val binding: ItemVotacaoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Dado) {
                binding.tvProposicaoVotacao.text = item.proposicaoObjeto
                binding.tvDescricaoVotacao.text = item.descricao
                binding.tvDataVotacao.text = item.dataHoraRegistro
                if(item.aprovacao == 1L){
                    binding.tvAprovadoVotacao.text = "Aprovado"
                    binding.tvAprovadoVotacao.setTextColor(Color.GREEN)
                }else{
                    binding.tvAprovadoVotacao.text = "Não aprovado"
                    binding.tvAprovadoVotacao.setTextColor(Color.RED)
                }
        }
    }
}