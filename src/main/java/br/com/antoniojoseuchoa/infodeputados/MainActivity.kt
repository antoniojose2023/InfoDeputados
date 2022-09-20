package br.com.antoniojoseuchoa.infodeputados

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.antoniojoseuchoa.infodeputados.databinding.ActivityMainBinding
import br.com.antoniojoseuchoa.infodeputados.databinding.ItemDetalheDeputadoDialogBinding
import br.com.antoniojoseuchoa.infodeputados.domain.Deputado
import br.com.antoniojoseuchoa.infodeputados.domain.deputadodetalhe.DeputadoDetalhe
import br.com.antoniojoseuchoa.infodeputados.view.AdapterDeputado
import br.com.antoniojoseuchoa.infodeputados.view.AdapterDeputadoDetalhe
import br.com.antoniojoseuchoa.infodeputados.viewmodel.DetalhesDeputadoViewModel
import br.com.antoniojoseuchoa.infodeputados.viewmodel.MainViewModel
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    View.OnClickListener, SearchView.OnQueryTextListener,
    AdapterDeputadoDetalhe.OnclickItemDeputado {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel by viewModels<MainViewModel>()
    private val detalhesDeputadoViewModel by viewModels<DetalhesDeputadoViewModel>()
    private lateinit var adapterDeputado : AdapterDeputado

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.elevation = .0f
        supportActionBar!!.title = "Info Parlamentares"


        binding.searchViewDeputado.setOnQueryTextListener(this)

        binding.swipeRefresh.setOnRefreshListener(this)

        retornaListaDeputados()


    }

    override fun onResume() {
        mainViewModel.getListDeputados()

        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
             R.id.menu_eventos ->{ startActivity(Intent(this, EventosActivity::class.java)) }
             R.id.menu_votaçao ->{startActivity(Intent(this, VotacaoActivity::class.java))}
             R.id.menu_proposicoes ->{startActivity(Intent(this, ProposicoesActivity::class.java))}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRefresh() {
        retornaListaDeputados()
    }

    fun retornarDeputado(){
            detalhesDeputadoViewModel.deputado.observe(this){
                    when(it){
                        DetalhesDeputadoViewModel.States.Loader -> {
                              binding.swipeRefresh.isRefreshing = true
                        }
                        is DetalhesDeputadoViewModel.States.Erro -> {
                            binding.swipeRefresh.isRefreshing = false
                        }
                        is DetalhesDeputadoViewModel.States.onSucess -> {
                            binding.swipeRefresh.isRefreshing = false

                            val item = it.deputadoDetalhe
                            val listDeputadoDetalhe = mutableListOf<DeputadoDetalhe>()
                            listDeputadoDetalhe.add( item )

                            binding.rvDeputados.layoutManager = LinearLayoutManager(this)
                            val adapterDeputadoDetalhe = AdapterDeputadoDetalhe(this, listDeputadoDetalhe, this)
                            binding.rvDeputados.adapter = adapterDeputadoDetalhe
                            adapterDeputado.notifyDataSetChanged()


                        }
                    }
            }
    }

    fun pesquisaDeputado(id: Long){
        detalhesDeputadoViewModel.getDetalhesDeputado(id)
        retornarDeputado()
    }

    fun retornaListaDeputados(){
        mainViewModel.deputado.observe(this, Observer {
            when(it){
                MainViewModel.States.Loader -> {
                        binding.swipeRefresh.isRefreshing = true
                }
                is MainViewModel.States.Erro -> {
                    Toast.makeText(this, "Erro ${it.erro}", Toast.LENGTH_LONG).show()
                    binding.swipeRefresh.isRefreshing = false
                }
                is MainViewModel.States.onSucess -> {
                    binding.rvDeputados.layoutManager = LinearLayoutManager(this)
                    adapterDeputado = AdapterDeputado(this, it.list as MutableList<Deputado>)
                    binding.rvDeputados.adapter = adapterDeputado
                    adapterDeputado.notifyDataSetChanged()

                    binding.swipeRefresh.isRefreshing = false

                }
            }
        })
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
          pesquisaDeputado(p0!!.toLong())
          return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }

    override fun onClick(p0: View?) {
        
    }

    override fun onclickItemDeputadoDetalhe(deputadoDetalhe: DeputadoDetalhe) {
            showDialog(deputadoDetalhe)
    }

    fun showDialog(deputadoDetalhe: DeputadoDetalhe){

        val view = ItemDetalheDeputadoDialogBinding.inflate(layoutInflater)
        view.tvNomeDeputadoDialog.text = deputadoDetalhe.dados.nomeCivil
        view.tvUfDeputadoDialog.text = deputadoDetalhe.dados.ufNascimento
        view.tvStatusDeputadoDialog.text = deputadoDetalhe.dados.ultimoStatus.condicaoEleitoral
        view.tvEscolaridadeDeputadoDialog.text = deputadoDetalhe.dados.escolaridade

        Glide.with(this).load(deputadoDetalhe.dados.ultimoStatus.urlFoto).into(view.ivFotoDeputadoDialog)

        view.btSiteDeputadoDialog.setOnClickListener {
            val url: String? = deputadoDetalhe.dados.urlWebsite
            if (url != null) {
                if(url.isEmpty() ){
                    Toast.makeText(this, "Site não existente", Toast.LENGTH_LONG).show()
                }else{
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setData(Uri.parse( url ))
                    startActivity( intent )
                }
            }

        }

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Detalhes deputado")
        alertDialog.setView( view.root )
        alertDialog.setPositiveButton("Fechar"){ v, k ->
            alertDialog.setCancelable(true)
        }
        alertDialog.create()
        alertDialog.show()

    }

}