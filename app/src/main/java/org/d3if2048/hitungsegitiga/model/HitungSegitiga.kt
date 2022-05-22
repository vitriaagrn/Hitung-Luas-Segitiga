package org.d3if2048.hitungsegitiga.model

import org.d3if2048.hitungsegitiga.db.LuasEntity

fun LuasEntity.hitungSegitiga(): HasilLuas {
    val luas = alas * tinggi / 2
    val kategori = when {
        luas < 100 -> KategoriLuas.KECIL
        luas <= 1000 -> KategoriLuas.SEDANG
        else -> KategoriLuas.BESAR
    }
    return HasilLuas(luas, kategori)
}