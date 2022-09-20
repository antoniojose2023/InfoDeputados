package br.com.antoniojoseuchoa.infodeputados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import br.com.antoniojoseuchoa.infodeputados.databinding.ActivityDetalhesDeputadoBinding
import br.com.antoniojoseuchoa.infodeputados.domain.Deputado
import br.com.antoniojoseuchoa.infodeputados.domain.deputadodetalhe.DeputadoDetalhe
import br.com.antoniojoseuchoa.infodeputados.viewmodel.DetalhesDeputadoViewModel
import com.bumptech.glide.Glide

class DetalhesDeputadoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetalhesDeputadoBinding.inflate(layoutInflater) }
    private val detalhesDeputadoViewModel by viewModels<DetalhesDeputadoViewModel>()

    lateinit var deputado: Deputado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

            val _deputado = intent.getSerializableExtra("deputado") as Deputado
            val id = _deputado.id
            detalhesDeputadoViewModel.getDetalhesDeputado(id)
            deputado = _deputado


      detalhesDeputadoViewModel.deputado.observe(this){
            when(it){
                DetalhesDeputadoViewModel.States.Loader -> {}
                is DetalhesDeputadoViewModel.States.Erro -> {
                    Toast.makeText(this, "Erro ${it.erro}", Toast.LENGTH_LONG).show()

                }
                is DetalhesDeputadoViewModel.States.onSucess -> {
                    binding.tvNomeDeputadoDetalhes.text = it.deputadoDetalhe.dados.nomeCivil
                    binding.tvPartidoDeputadoDetalhes.text = it.deputadoDetalhe.dados.ultimoStatus.siglaPartido
                    binding.tvNomeUfDetalhes.text = it.deputadoDetalhe.dados.ultimoStatus.siglaUf
                    binding.tvSituacaoDeputadoDetalhes.text = it.deputadoDetalhe.dados.ultimoStatus.situacao
                    binding.tvCondicaoEleitoralDeputadoDetalhes.text = it.deputadoDetalhe.dados.ultimoStatus.condicaoEleitoral
                    binding.tvEscolaridadeDeputadoDetalhes.text = it.deputadoDetalhe.dados.escolaridade
                    Glide.with(this).load( it.deputadoDetalhe.dados.ultimoStatus.urlFoto ).into(binding.ivFotoDeputadoDetalhes)

                }
            }
        }

        binding.buttonDespesas.setOnClickListener {
            val intent = Intent(this, GraficoDespesasDeputadoActivity::class.java)
            intent.putExtra("deputado", deputado)
            startActivity(intent)
        }

       binding.ivFechar.setOnClickListener {
             finish()
             startActivity(Intent(this, MainActivity::class.java))
             overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
       }

    }
}