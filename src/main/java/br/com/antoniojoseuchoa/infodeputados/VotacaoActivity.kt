package br.com.antoniojoseuchoa.infodeputados

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.antoniojoseuchoa.infodeputados.databinding.ActivityVotacaoBinding
import br.com.antoniojoseuchoa.infodeputados.view.AdapterVotacao
import br.com.antoniojoseuchoa.infodeputados.viewmodel.VotacaoViewModel

class VotacaoActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private val binding by lazy { ActivityVotacaoBinding.inflate(layoutInflater) }
    private val votacaoViewModel by viewModels<VotacaoViewModel>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.title = "Votação da câmara"

        binding.swipeRefresh.setOnRefreshListener(this)

        getListVotacao()


    }

    override fun onResume() {
        votacaoViewModel.getListVotacao()
        super.onResume()
    }

    fun getListVotacao(){
        votacaoViewModel.votacao.observe(this){
            when(it){
                VotacaoViewModel.States.Loader -> {
                    binding.swipeRefresh.isRefreshing = true
                }
                is VotacaoViewModel.States.Erro -> {
                    binding.swipeRefresh.isRefreshing = false
                    Toast.makeText(this, "${it.erro}", Toast.LENGTH_LONG).show()
                }
                is VotacaoViewModel.States.onSucess -> {
                    binding.swipeRefresh.isRefreshing = false
                    binding.rvVotacao.layoutManager = LinearLayoutManager(this)
                    val adapterVotacao = AdapterVotacao(this, it.list)
                    binding.rvVotacao.adapter = adapterVotacao

                }
            }
        }
    }

    override fun onRefresh() {
         getListVotacao()
    }

}