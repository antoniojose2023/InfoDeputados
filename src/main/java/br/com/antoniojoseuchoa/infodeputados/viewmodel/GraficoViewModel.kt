package br.com.antoniojoseuchoa.infodeputados.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.antoniojoseuchoa.infodeputados.data.RepositoryDeputados
import br.com.antoniojoseuchoa.infodeputados.domain.despesas.Dado
import br.com.antoniojoseuchoa.infodeputados.domain.despesas.Despesas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GraficoViewModel: ViewModel() {
    private var _despesa = MutableLiveData<States>()
    val despesa: LiveData<States> = _despesa

    fun getListDespesas(id: Int){

        _despesa.value = States.Loader

        RepositoryDeputados.retrofit.getListDespesas(id).enqueue(object : Callback<Despesas> {
            override fun onResponse(call: Call<Despesas>, response: Response<Despesas>) {
                if(response.isSuccessful){
                    response.body()!!.dados.let {
                        _despesa.value = States.onSucess(it)
                    }


                }else{
                    _despesa.value = States.Erro(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<Despesas>, t: Throwable) {
                _despesa.value = States.Erro(t.message.toString())
            }

        })
    }

    sealed class States {
        object Loader: States()
        class Erro(var erro: String) : States()
        class onSucess(var list: List<Dado>) : States()
    }
}