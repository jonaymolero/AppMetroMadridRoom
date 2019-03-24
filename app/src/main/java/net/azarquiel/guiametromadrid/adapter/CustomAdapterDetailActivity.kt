package net.azarquiel.guiametromadrid.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.rowestaciones.view.*
import net.azarquiel.guiametromadrid.model.EstacionView
import net.azarquiel.guiametromadrid.model.LineaView

class CustomAdapterDetailActivity(
        val context: Context,
        val layout: Int,
        val linea:LineaView,
        val dataList:ArrayList<EstacionView>
) : RecyclerView.Adapter<CustomAdapterDetailActivity.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item,linea)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder( viewlayout: View, val context: Context
    ) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: EstacionView,linea:LineaView){
            itemView.tvNombreEstacionDetail.text=dataItem.nombre
            itemView.lyCorrespondencia.removeAllViews()
            for(linea in dataItem.lineas){
                var imagen=ImageView(context)
                var id=context.resources.getIdentifier("icono_linea_$linea","drawable", context.packageName)
                imagen.setImageResource(id)
                itemView.lyCorrespondencia.addView(imagen)
            }
            itemView.cvEstaciones.setCardBackgroundColor(Color.parseColor(linea.color.toString().replace("#FF" , "#66").replace("#A0","#44")))
        }
    }
}