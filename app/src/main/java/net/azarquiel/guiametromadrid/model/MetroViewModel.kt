package net.azarquiel.guiametromadrid.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class ViewLineaViewModel (application: Application) : AndroidViewModel(application) {

    private var lineaViewRepository: LineaViewRepository = LineaViewRepository(application)

    var allLineas: LiveData<List<LineaView>> = lineaViewRepository.allLineas

}

class EstacionViewModel (application: Application) : AndroidViewModel(application) {
    private var estacionRepository:EstacionRepository= EstacionRepository(application)

    fun estacionesDeUnaLinea(linea:Int):LiveData<List<Estacion>>{
        return estacionRepository.estacionDeUnaLinea(linea)
    }
}

class CorrespondenciaViewModel (application: Application) : AndroidViewModel(application) {
    private var correspondenciaRepository:CorrespondenciaRepository= CorrespondenciaRepository(application)

    fun estacionesConCorrespondencia(nombre:String,linea:Int):List<Int>{
        return correspondenciaRepository.estacionConCorrespondencia(nombre,linea)
    }
}
