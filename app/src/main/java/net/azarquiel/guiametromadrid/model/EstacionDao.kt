package net.azarquiel.guiametromadrid.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
interface EstacionDao {
    @Query("SELECT * from estacion WHERE linea=:linea ORDER BY id ASC")
    fun getEstacionesPorLinea(linea:Int): LiveData<List<Estacion>>
}