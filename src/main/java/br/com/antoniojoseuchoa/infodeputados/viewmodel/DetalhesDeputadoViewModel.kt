package br.com.antoniojoseuchoa.infodeputados.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.antoniojoseuchoa.infodeputados.data.RepositoryDeputados

import br.com.antoniojoseuchoa.infodeputados.domain.deputadodetalhe.DeputadoDetalhe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalhesDeputadoViewModel: ViewModel() {

    private var _deputados = MutableLiveData<States>()
    val deputado: LiveData<States> = _deputados

    fun getDetalhesDeputado(id: Long){

        _deputados.value = States.Loader

        RepositoryDeputados.retrofit.getDetalhesDeputado(id).enqueue(object : Callback<DeputadoDetalhe>{
            override fun onResponse(call: Call<DeputadoDetalhe>, response: Response<DeputadoDetalhe>) {
                    if(response.isSuccessful){
                           _deputados.value = States.onSucess(response.body()!!)
                    }else{
                        _deputados.value = States.Erro(response.errorBody().toString())
                    }
            }

            override fun onFailure(call: Call<DeputadoDetalhe>, t: Throwable) {
                    _deputados.value = States.Erro(t.message.toString())
            }

        })
    }

    sealed class States {
        object Loader: States()
        class Erro(var erro: String) : States()
        class onSucess(var deputadoDetalhe: DeputadoDetalhe) : States()
    }

}