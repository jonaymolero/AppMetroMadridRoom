package net.azarquiel.guiametromadrid.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rowlineas.view.*
import net.azarquiel.guiametromadrid.model.LineaView

class CustomAdapter(
        val context: Context,
        val layout: Int
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var dataList: List<LineaView> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setLineasView(lineas: List<LineaView>) {
        this.dataList = lineas
        notifyDataSetChanged()
    }

    class ViewHolder( viewlayout: View, val context: Context
    ) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: LineaView){
            var id=context.resources.getIdentifier("icono_linea_${dataItem.id}","drawable",context.packageName)
            itemView.ivLineaRow.setImageResource(id)
            itemView.tvLineaRow.text=dataItem.nombre
            itemView.tvEstacionesRow.text="${dataItem.inicial}-${dataItem.ultima}"
            if(dataItem.id==99){
                itemView.setBackgroundColor(Color.parseColor("#C7C2C2"))
            }else{
                itemView.setBackgroundColor(Color.parseColor(dataItem.color.toString()))
            }
            itemView.tag=dataItem
        }
    }
}
