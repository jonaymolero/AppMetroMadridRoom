package net.azarquiel.guiametromadrid.model

import android.app.Application
import android.arch.lifecycle.LiveData

class LineaViewRepository(application: Application) {

    val lineaViewDao = MetroDB.getDatabase(application)!!.lineaViewDao()
    // select
    val allLineas: LiveData<List<LineaView>> = lineaViewDao.getAllLineasConEstaciones()

}

class EstacionRepository(application: Application){
    val estacionDao = MetroDB.getDatabase(application)!!.estacionDao()
    //select
    fun estacionDeUnaLinea(linea:Int):LiveData<List<Estacion>>{
        return estacionDao.getEstacionesPorLinea(linea)
    }
}

class CorrespondenciaRepository(application: Application){
    val correspondenciaDao=MetroDB.getDatabase(application)!!.correspondenciaDao()

    fun estacionConCorrespondencia(nombre:String,linea:Int):List<Int>{
        return correspondenciaDao.getEstacionesConCorrespondencia(nombre,linea)
    }
}
