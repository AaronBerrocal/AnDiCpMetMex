package com.aleph5.andicpmetmex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.aleph5.andicpmetmex.R
import com.aleph5.andicpmetmex.datamodels.EventData
import kotlinx.android.synthetic.main.item_event.view.*

class AdministracionEventosAdapter(private val eventDataList: ArrayList<EventData>) : Adapter<AdministracionEventosAdapter.AdministracionEventosViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdministracionEventosViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_event,
            parent,
            false
        )

        return AdministracionEventosViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: AdministracionEventosViewHolder,
        position: Int
    ) {

        val currentItem = eventDataList[position]

        holder.idTv.text = currentItem.idEvento
        holder.dateTv.text = currentItem.fechaReporte
        holder.priorityTv.text = currentItem.prioridad
        holder.statusTv.text = currentItem.estatus
        holder.plantTv.text = currentItem.planta
        holder.areaTv.text = currentItem.area
        holder.subareaTv.text = currentItem.subarea
        holder.equipmentTv.text = currentItem.equipoPlanta
        holder.systemTypeTv.text = currentItem.tipoSistema
        holder.eventTypeTv.text = currentItem.tipoEvento
    }

    override fun getItemCount() = eventDataList.size

    class AdministracionEventosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val idTv: TextView = itemView.txt_event_id_value
        val dateTv: TextView = itemView.txt_event_date_value
        val priorityTv: TextView = itemView.txt_event_priority_value
        val statusTv: TextView = itemView.txt_event_status_value
        val plantTv: TextView = itemView.txt_event_planta_value
        val areaTv: TextView = itemView.txt_event_area_value
        val subareaTv: TextView = itemView.txt_event_subarea_value
        val equipmentTv: TextView = itemView.txt_event_equipment_value
        val systemTypeTv: TextView = itemView.txt_event_system_type_value
        val eventTypeTv: TextView = itemView.txt_event_type_value

    }

}