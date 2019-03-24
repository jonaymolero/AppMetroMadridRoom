package net.azarquiel.guiametromadrid.view

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail.*
import net.azarquiel.guiametromadrid.R
import net.azarquiel.guiametromadrid.adapter.CustomAdapterDetailActivity
import net.azarquiel.guiametromadrid.model.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailActivity : AppCompatActivity() {

    private lateinit var estacionesViewModel: EstacionViewModel
    private lateinit var correspondenciaViewModel: CorrespondenciaViewModel
    private lateinit var linea:LineaView
    private lateinit var adapter:CustomAdapterDetailActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        linea=intent.getSerializableExtra("lineaPulsada") as LineaView
        title="${linea.nombre} ${linea.inicial}-${linea.ultima}"
        estacionesViewModel = ViewModelProviders.of(this).get(EstacionViewModel::class.java)
        correspondenciaViewModel=ViewModelProviders.of(this).get(CorrespondenciaViewModel::class.java)
        estacionesViewModel.estacionesDeUnaLinea(linea.id).observe(this, Observer { estaciones ->
            // Update the cached copy of the products in the adapter.
            estaciones?.let { getCorrespondencia(it) }
        })
    }

    private fun getCorrespondencia(estaciones:List<Estacion>){
        val arrayEstacion=ArrayList<EstacionView>()
        doAsync {
            for(estacion in estaciones) {
                arrayEstacion.add(EstacionView(estacion.id, estacion.nombre, correspondenciaViewModel.estacionesConCorrespondencia(estacion.nombre, linea.id)))
            }
            uiThread {
                showData(arrayEstacion)
            }
        }
    }

    private fun showData(estacionesView:ArrayList<EstacionView>){
        adapter= CustomAdapterDetailActivity(this,R.layout.rowestaciones,linea,estacionesView)
        rvEstaciones.layoutManager=LinearLayoutManager(this)
        rvEstaciones.adapter=adapter
    }
}
