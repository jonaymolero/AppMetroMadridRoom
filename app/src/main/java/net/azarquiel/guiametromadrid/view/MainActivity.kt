package net.azarquiel.guiametromadrid.view

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.arch.lifecycle.Observer
import android.content.Intent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import net.azarquiel.guiametromadrid.R
import net.azarquiel.guiametromadrid.adapter.CustomAdapter
import net.azarquiel.guiametromadrid.model.LineaView
import net.azarquiel.guiametromadrid.model.ViewLineaViewModel
import net.azarquiel.guiametromadrid.utilidades.Util

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CustomAdapter
    private lateinit var viewLineaViewModel:ViewLineaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Util.inyecta(this)
        viewLineaViewModel = ViewModelProviders.of(this).get(ViewLineaViewModel::class.java)
        showData()
        viewLineaViewModel.allLineas.observe(this, Observer { lineas ->
            // Update the cached copy of the products in the adapter.
            lineas?.let { adapter.setLineasView(it) }
        })
    }

    private fun showData() {
        adapter = CustomAdapter(this, R.layout.rowlineas)
        rvLineas.layoutManager = LinearLayoutManager(this)
        rvLineas.adapter = adapter
    }

    fun pulsaLinea(v:View){
        val linea=v.tag as LineaView
        val intent=Intent(this,DetailActivity::class.java)
        intent.putExtra("lineaPulsada",linea)
        startActivity(intent)
    }
}
