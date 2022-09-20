package br.com.antoniojoseuchoa.infodeputados.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.antoniojoseuchoa.infodeputados.databinding.ItemDeputadoBinding
import br.com.antoniojoseuchoa.infodeputados.domain.deputadodetalhe.DeputadoDetalhe
import com.bumptech.glide.Glide

class AdapterDeputadoDetalhe(val context: Context, var list: MutableList<DeputadoDetalhe>, val onclickItemDeputado: OnclickItemDeputado): RecyclerView.Adapter<AdapterDeputadoDetalhe.ViewHolder>() {

     var onclickItemDep: OnclickItemDeputado = onclickItemDeputado

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val layoutInflater = LayoutInflater.from( context )
             val binding = ItemDeputadoBinding.inflate(layoutInflater, parent, false)
             return ViewHolder( binding )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = list[position]
            holder.binding.tvIdDeputado.text = item.dados.id.toString()
            holder.binding.tvNomeDeputado.text = item.dados.nomeCivil
            holder.binding.tvPartido.text = item.dados.ultimoStatus.siglaPartido
            Glide.with(holder.binding.root.context).load( item.dados.ultimoStatus.urlFoto ).into(holder.binding.ivFotoDeputado)

            holder.binding.root.setOnClickListener{
                onclickItemDep.onclickItemDeputadoDetalhe( item )
            }

    }

    override fun getItemCount() = list.size

    open class ViewHolder(val binding: ItemDeputadoBinding): RecyclerView.ViewHolder(binding.root){

    }

    interface OnclickItemDeputado{
        fun onclickItemDeputadoDetalhe(deputadoDetalhe: DeputadoDetalhe)
    }
}