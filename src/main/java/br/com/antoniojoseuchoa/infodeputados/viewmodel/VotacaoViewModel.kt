package br.com.antoniojoseuchoa.infodeputados.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.antoniojoseuchoa.infodeputados.data.RepositoryDeputados
import br.com.antoniojoseuchoa.infodeputados.domain.votacao.DadoVotacao
import br.com.antoniojoseuchoa.infodeputados.domain.votacao.Dado
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VotacaoViewModel: ViewModel() {
    private var _votacao = MutableLiveData<States>()
    val votacao: LiveData<States> = _votacao

    fun getListVotacao(){

        _votacao.value = States.Loader

        RepositoryDeputados.retrofit.getListVotacao().enqueue(object : Callback<DadoVotacao> {
            override fun onResponse(call: Call<DadoVotacao>, response: Response<DadoVotacao>) {
                if(response.isSuccessful){
                    response.body()!!.dados.let {
                        _votacao.value = States.onSucess(it)
                    }


                }else{
                    _votacao.value = States.Erro(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<DadoVotacao>, t: Throwable) {
                _votacao.value = States.Erro(t.message.toString())
            }

        })
    }

    sealed class States {
        object Loader: States()
        class Erro(var erro: String) : States()
        class onSucess(var list: List<Dado>) : States()
    }
}