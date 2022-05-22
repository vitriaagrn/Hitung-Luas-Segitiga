package org.d3if2048.hitungsegitiga.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao

interface LuasDao {
    @Insert
    fun insert(luas: LuasEntity)

    @Query("SELECT * FROM luas ORDER BY id DESC")
    fun getLastLuas(): LiveData<List<LuasEntity>>

    @Query("DELETE FROM luas")
    fun clearData()
}