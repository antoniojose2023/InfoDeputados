package br.com.antoniojoseuchoa.infodeputados.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.antoniojoseuchoa.infodeputados.data.RepositoryDeputados
import br.com.antoniojoseuchoa.infodeputados.domain.Dado
import br.com.antoniojoseuchoa.infodeputados.domain.Deputado
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private var _deputados = MutableLiveData<States>()
    val deputado: LiveData<States> = _deputados

    fun getListDeputados(){

        _deputados.value = States.Loader

        RepositoryDeputados.retrofit.getListaDeputados().enqueue(object : Callback<Dado>{
            override fun onResponse(call: Call<Dado>, response: Response<Dado>) {
                 if(response.isSuccessful){
                       var res = response.body()
                       _deputados.value = States.onSucess(res!!.dados)
                 }
            }

            override fun onFailure(call: Call<Dado>, t: Throwable) {
                   _deputados.value = States.Erro(t.message.toString())
            }


        })
    }

    sealed class States {
        object Loader: States()
        class Erro(var erro: String) : States()
        class onSucess(var list: List<Deputado>) : States()
    }



}