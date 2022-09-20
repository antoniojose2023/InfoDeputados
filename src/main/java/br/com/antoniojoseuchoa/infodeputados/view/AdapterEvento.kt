package br.com.antoniojoseuchoa.infodeputados.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.antoniojoseuchoa.infodeputados.databinding.ItemEventoBinding
import br.com.antoniojoseuchoa.infodeputados.domain.eventos.Evento
import br.com.antoniojoseuchoa.infodeputados.domain.eventos.Dado


class AdapterEvento(val context: Context, var list: List<Dado>): RecyclerView.Adapter<AdapterEvento.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from( context )
        val binding = ItemEventoBinding.inflate(layoutInflater, parent, false)
        return ViewHolder( binding )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind( item)
    }

    override fun getItemCount() = list.size

    open class ViewHolder(val binding: ItemEventoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Dado){
            binding.tvDataInicioEvento.text = item.dataHoraInicio
            binding.tvDataFimEvento.text = item.dataHoraFim
            binding.tvTipoEvento.text = item.descricaoTipo
            binding.tvDescricaoEvento.text = item.descricao

        }
    }
}