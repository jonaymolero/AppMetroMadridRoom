package net.azarquiel.guiametromadrid.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
interface CorrespondenciaDao {
    @Query("SELECT linea from estacion where nombre=:estacion and linea!=:linea")
    fun getEstacionesConCorrespondencia(estacion:String,linea:Int): List<Int>
}