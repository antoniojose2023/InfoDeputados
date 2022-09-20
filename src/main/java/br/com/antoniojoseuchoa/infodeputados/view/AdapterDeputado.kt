package br.com.antoniojoseuchoa.infodeputados.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.antoniojoseuchoa.infodeputados.DetalhesDeputadoActivity
import br.com.antoniojoseuchoa.infodeputados.databinding.ItemDeputadoBinding
import br.com.antoniojoseuchoa.infodeputados.domain.Dado
import br.com.antoniojoseuchoa.infodeputados.domain.Deputado
import com.bumptech.glide.Glide

class AdapterDeputado(val context: Context, var list: MutableList<Deputado>): RecyclerView.Adapter<AdapterDeputado.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val layoutInflater = LayoutInflater.from( context )
             val binding = ItemDeputadoBinding.inflate(layoutInflater, parent, false)
             return ViewHolder( binding )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = list[position]
            holder.bind( item )
    }

    override fun getItemCount() = list.size

    open class ViewHolder(val binding: ItemDeputadoBinding): RecyclerView.ViewHolder(binding.root){
            fun bind(item: Deputado){
                 binding.tvNomeDeputado.text = item.nome
                 binding.tvPartido.text = item.siglaPartido
                 Glide.with(binding.root.context).load( item.urlFoto ).into(binding.ivFotoDeputado)

                 binding.root.setOnClickListener {
                     val intent = Intent(binding.root.context, DetalhesDeputadoActivity::class.java)
                     intent.putExtra("deputado", item)
                     binding.root.context.startActivity( intent )
                 }
            }
    }

}