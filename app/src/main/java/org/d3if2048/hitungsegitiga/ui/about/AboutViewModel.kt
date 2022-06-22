package org.d3if2048.hitungsegitiga.ui.about

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2048.hitungsegitiga.network.ApiStatus
import org.d3if2048.hitungsegitiga.network.SegitigaApi

class AboutViewModel(private val api: SegitigaApi) : ViewModel() {

    private var data = MutableLiveData<String>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)


            try{
                val response = api.service.getSegitiga()

                api.service.getSegitiga().body()?.copyright.toString().let { Log.i("Data: ", it) }
                data.postValue(response.body()?.copyright.toString())
                status.postValue(ApiStatus.SUCCESS)
            }catch (e:Exception) {
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    val getData: LiveData<String> get() = (data)

    fun getStatus(): LiveData<ApiStatus> = status

}