package br.com.antoniojoseuchoa.infodeputados.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.antoniojoseuchoa.infodeputados.data.RepositoryDeputados
import br.com.antoniojoseuchoa.infodeputados.domain.eventos.Dado
import br.com.antoniojoseuchoa.infodeputados.domain.eventos.Evento


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventoViewModel: ViewModel() {

    private var _evento = MutableLiveData<States>()
    val evento: LiveData<States> = _evento

    fun getListEvento(){

        _evento.value = States.Loader

        RepositoryDeputados.retrofit.getListEvento().enqueue(object : Callback<Evento>{
            override fun onResponse(call: Call<Evento>, response: Response<Evento>) {
                if(response.isSuccessful){
                        _evento.value = States.onSucess(response.body()?.dados!!)
                }else{
                        _evento.value = States.Erro(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<Evento>, t: Throwable) {
                   _evento.value = States.Erro(t.message.toString())
            }
        })
    }

    sealed class States {
        object Loader: States()
        class Erro(var erro: String) : States()
        class onSucess(var eventos: List<Dado>) : States()
    }

}