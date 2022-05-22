package org.d3if2048.hitungsegitiga.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.d3if2048.hitungsegitiga.model.KategoriLuas

@Entity(tableName = "luas")

data class LuasEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var alas: Double,
    var tinggi: Double
)
