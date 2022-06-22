package org.d3if2048.hitungsegitiga.ui.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if2048.hitungsegitiga.network.SegitigaApi
import java.lang.IllegalArgumentException

class AboutViewModelFactory(

    private val api: SegitigaApi
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AboutViewModel::class.java)) {
            return AboutViewModel(api) as T
        }
        throw IllegalArgumentException ("Unknown ViewModel Class")
    }
}