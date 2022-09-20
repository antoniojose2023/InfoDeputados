package br.com.antoniojoseuchoa.infodeputados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.antoniojoseuchoa.infodeputados.databinding.ActivityProposicoesBinding
import br.com.antoniojoseuchoa.infodeputados.view.AdapterProposicoes
import br.com.antoniojoseuchoa.infodeputados.viewmodel.ProposicoesViewModel

class ProposicoesActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private val binding by lazy { ActivityProposicoesBinding.inflate(layoutInflater) }
    private val proposicoesViewModel by viewModels<ProposicoesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.title = "Proposições"

        binding.swipeRefresh.setOnRefreshListener(this)

        getListProposicoes()

    }

    override fun onResume() {
        proposicoesViewModel.getListProposicoes()
        super.onResume()
    }

    fun getListProposicoes(){
        proposicoesViewModel.proposicoes.observe(this){
            when(it){
                ProposicoesViewModel.States.Loader -> {
                    binding.swipeRefresh.isRefreshing = true
                }
                is ProposicoesViewModel.States.Erro -> {
                    binding.swipeRefresh.isRefreshing = false
                }
                is ProposicoesViewModel.States.onSucess -> {
                    binding.swipeRefresh.isRefreshing = false
                    binding.rvProposicoes.layoutManager = LinearLayoutManager(this)
                    val adapterProposicoes = AdapterProposicoes(this, it.list)
                    binding.rvProposicoes.adapter = adapterProposicoes
                }
            }
        }
    }

    override fun onRefresh() {
        getListProposicoes()
    }
}