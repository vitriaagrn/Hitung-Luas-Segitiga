package org.d3if2048.hitungsegitiga.ui.luas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2048.hitungsegitiga.db.LuasDao
import org.d3if2048.hitungsegitiga.db.LuasEntity
import org.d3if2048.hitungsegitiga.model.HasilLuas
import org.d3if2048.hitungsegitiga.model.KategoriLuas
import org.d3if2048.hitungsegitiga.model.hitungSegitiga

class HitungViewModel(private val db: LuasDao) : ViewModel() {

    private val hasilLuas = MutableLiveData<HasilLuas?>()
    private val navigasi = MutableLiveData<KategoriLuas?>()

    fun hitungLuas(alas: Double, tinggi: Double) {
        val dataLuas = LuasEntity (
            alas = alas,
            tinggi = tinggi
        )
        hasilLuas.value = dataLuas.hitungSegitiga()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataLuas)
            }
        }
    }

    fun getHasilLuas(): LiveData<HasilLuas?> = hasilLuas

    fun mulaiNavigasi() {
        navigasi.value = hasilLuas.value?.kategoriLuas
    }

    fun selesaiNavigasi() {
        navigasi.value = null
    }

    fun getNavigasi() : LiveData<KategoriLuas?> = navigasi
}