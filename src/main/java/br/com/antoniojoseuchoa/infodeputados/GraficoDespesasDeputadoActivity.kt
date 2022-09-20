package br.com.antoniojoseuchoa.infodeputados

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.antoniojoseuchoa.infodeputados.databinding.ActivityGraficoDespesasDeputadoBinding
import br.com.antoniojoseuchoa.infodeputados.domain.Deputado
import br.com.antoniojoseuchoa.infodeputados.domain.despesas.Dado
import br.com.antoniojoseuchoa.infodeputados.view.AdapterDespesas
import br.com.antoniojoseuchoa.infodeputados.viewmodel.GraficoViewModel
import com.github.mikephil.charting.data.*

class GraficoDespesasDeputadoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityGraficoDespesasDeputadoBinding.inflate(layoutInflater) }
    private val graficoViewModel by viewModels<GraficoViewModel>()
    private lateinit var deputado: Deputado

    private var listDados = listOf<Dado>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.title = "Despesas"
        deputado = intent.getSerializableExtra("deputado") as Deputado

        binding.tvNomeDeputadoDespesas.text = deputado.nome

        graficoViewModel.despesa.observe(this){
            when(it){
                GraficoViewModel.States.Loader -> {

                }
                is GraficoViewModel.States.Erro -> {
                    Toast.makeText(this, "Erro ${it.erro}", Toast.LENGTH_LONG).show()
                    Log.i("erro", "erro no grafico "+it.erro)
                }
                is GraficoViewModel.States.onSucess -> {
                     listDados = it.list
                     preencherRecyclerViewDespesas(listDados)

                    //carregando os dados para o grafico
                    val despesas = ArrayList<PieEntry>()

                    for(despesa in listDados.subList(0,9)){
                        lateinit var mes: String
                        when(despesa.mes.toInt()){
                            1 -> {  mes = "jan"  }
                            2 -> {  mes = "fev"  }
                            3 -> {  mes = "mar"  }
                            4 -> {  mes = "abr"  }
                            5 -> {  mes = "mai"  }
                            6 -> {  mes = "jun"  }
                            7 -> {  mes = "jul"  }
                            8 -> {  mes = "ago"  }
                            9 -> {  mes = "set"  }
                            10 -> {  mes = "out"  }
                            11 -> {  mes = "nov"  }
                            12 -> {  mes = "dez"  }
                        }

                        despesas.add(PieEntry(despesa.valorLiquido.toFloat(), mes))
                    }

                    var pieDataSet = PieDataSet(despesas, "")
                    pieDataSet.valueTextColor = Color.WHITE
                    var listCores = arrayListOf<Int>(Color.BLUE, Color.GREEN, Color.GRAY, Color.RED, Color.BLACK, Color.MAGENTA)
                    pieDataSet.color = Color.BLUE
                    pieDataSet.valueTextSize = 16f
                    pieDataSet.colors = listCores
                    pieDataSet.label


                    var pieData = PieData(pieDataSet)

                    binding.chart.data = pieData
                    binding.chart.invalidate()
                }
            }
        }


    }

    override fun onResume() {
        graficoViewModel.getListDespesas(deputado.id.toInt())
        super.onResume()
    }

    fun preencherRecyclerViewDespesas(list: List<Dado>){
            binding.rvDespesas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            val adapterDespesas = AdapterDespesas(this, list)
            binding.rvDespesas.adapter = adapterDespesas
    }

}