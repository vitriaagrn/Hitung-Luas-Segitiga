package org.d3if2048.hitungsegitiga.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2048.hitungsegitiga.db.LuasDao

class HistoriViewModel (private val db: LuasDao) : ViewModel() {

    val data = db.getLastLuas()
    fun deleteData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.clearData()
            }
        }
    }
}