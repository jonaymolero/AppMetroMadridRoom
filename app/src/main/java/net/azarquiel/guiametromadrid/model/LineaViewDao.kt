package net.azarquiel.guiametromadrid.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
interface LineaViewDao {
    @Query("SELECT li.id, li.nombre, li.color, es.nombre inicial, est.nombre ultima FROM linea li, estacion es, estacion est where li.id=es.linea and li.id=est.linea and es.nombre=(select nombre from estacion es where id=(select min(id) from estacion es where li.id=es.linea)) and est.nombre=(select nombre from estacion est where id=(select max(id) from estacion est where li.id=est.linea))")
    fun getAllLineasConEstaciones(): LiveData<List<LineaView>>
}