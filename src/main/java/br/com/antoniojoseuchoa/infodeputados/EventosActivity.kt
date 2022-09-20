package br.com.antoniojoseuchoa.infodeputados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.antoniojoseuchoa.infodeputados.databinding.ActivityEventosBinding
import br.com.antoniojoseuchoa.infodeputados.view.AdapterEvento
import br.com.antoniojoseuchoa.infodeputados.viewmodel.EventoViewModel

class EventosActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private val binding by lazy {ActivityEventosBinding.inflate(layoutInflater) }
    private val eventoViewModel by viewModels<EventoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.title = "Eventos da câmara"

        binding.swipeRefresh.setOnRefreshListener(this)

        getListEvento()

    }

    override fun onResume() {
        eventoViewModel.getListEvento()
        super.onResume()
    }

    fun getListEvento(){
        eventoViewModel.evento.observe(this){
            when(it){
                EventoViewModel.States.Loader -> {
                    binding.swipeRefresh.isRefreshing = true
                }
                is EventoViewModel.States.Erro -> {
                    binding.swipeRefresh.isRefreshing = false
                    Toast.makeText(this, "${it.erro}", Toast.LENGTH_LONG).show()

                }
                is EventoViewModel.States.onSucess -> {
                    binding.swipeRefresh.isRefreshing = false
                    binding.rvEventos.layoutManager = LinearLayoutManager(this)
                    val adapterEvento = AdapterEvento(this, it.eventos)
                    binding.rvEventos.adapter = adapterEvento

                }
            }
        }
    }

    override fun onRefresh() {
       getListEvento()
    }

}