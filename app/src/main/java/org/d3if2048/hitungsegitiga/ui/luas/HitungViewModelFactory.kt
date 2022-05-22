package org.d3if2048.hitungsegitiga.ui.luas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if2048.hitungsegitiga.db.LuasDao
import java.lang.IllegalArgumentException

class HitungViewModelFactory (

    private val db: LuasDao
    ) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HitungViewModel::class.java)) {
            return HitungViewModel(db) as T
        }
        throw IllegalArgumentException ("Unknown ViewModel Class")
    }
}