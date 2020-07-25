package com.aleph5.andicpmetmex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.aleph5.andicpmetmex.R
import com.aleph5.andicpmetmex.datamodels.PartData
import kotlinx.android.synthetic.main.item_part.view.*

class InventarioRefaccionesAdapter(private val partDataList: ArrayList<PartData>) : Adapter<InventarioRefaccionesAdapter.InventarioRefaccionesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InventarioRefaccionesViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_part,
            parent,
            false
        )

        return InventarioRefaccionesViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: InventarioRefaccionesViewHolder,
        position: Int
    ) {

        val currentItem = partDataList[position]

        holder.serialTv.text = currentItem.numero_serie
        holder.partNameTv.text = currentItem.refaccion
        holder.statusTv.text = currentItem.estatus
        holder.inventoryTv.text = currentItem.inventario
        holder.shelfTv.text = currentItem.gaveta.toString()
        holder.boxTv.text = currentItem.caja.toString()
        holder.registerDateTv.text = currentItem.fechaRegistro

        holder.statusTv.setTextColor(
            ContextCompat.getColor(
                holder.itemView.context,
                when(currentItem.estatus){
                    "Disponible" -> R.color.colorGreenCheck
                    "Apartada" -> R.color.colorYellowCheck
                    "No Disponible" -> R.color.colorRedCheck
                    else -> R.color.colorRedCheck
                }
            )
        )

    }

    override fun getItemCount() = partDataList.size

    class InventarioRefaccionesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val serialTv: TextView = itemView.txt_part_serial_value
        val partNameTv: TextView = itemView.txt_part_name_value
        val statusTv: TextView = itemView.txt_part_status_value
        val inventoryTv: TextView = itemView.txt_part_inventory_value
        val shelfTv: TextView = itemView.txt_part_shelf_value
        val boxTv: TextView = itemView.txt_part_box_value
        val registerDateTv: TextView = itemView.txt_part_register_date_value

    }
}