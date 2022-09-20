package br.com.antoniojoseuchoa.infodeputados.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.antoniojoseuchoa.infodeputados.data.RepositoryDeputados
import br.com.antoniojoseuchoa.infodeputados.domain.proposicoes.Dado
import br.com.antoniojoseuchoa.infodeputados.domain.proposicoes.Proposicoes


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProposicoesViewModel: ViewModel() {

    private var _proposicoes = MutableLiveData<States>()
    val proposicoes: LiveData<States> = _proposicoes

    fun getListProposicoes(){

        _proposicoes.value = States.Loader

        RepositoryDeputados.retrofit.getListProposicoes().enqueue(object : Callback<Proposicoes> {
            override fun onResponse(call: Call<Proposicoes>, response: Response<Proposicoes>) {
                if(response.isSuccessful){
                    response.body()!!.dados.let {
                        _proposicoes.value = States.onSucess(it)
                    }


                }else{
                    _proposicoes.value = States.Erro(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<Proposicoes>, t: Throwable) {
                _proposicoes.value = States.Erro(t.message.toString())
            }

        })
    }

    sealed class States {
        object Loader: States()
        class Erro(var erro: String) : States()
        class onSucess(var list: List<Dado>) : States()
    }
}