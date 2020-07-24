package com.aleph5.andicpmetmex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.aleph5.andicpmetmex.R
import com.aleph5.andicpmetmex.datamodels.ProjectData
import kotlinx.android.synthetic.main.item_project.view.*

class AdministracionProyectosAdapter(private val projectDataList: ArrayList<ProjectData>) : Adapter<AdministracionProyectosAdapter.AdministracionProyectosViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdministracionProyectosViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_project,
            parent,
            false
        )

        return AdministracionProyectosViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: AdministracionProyectosViewHolder,
        position: Int
    ) {

        val currentItem = projectDataList[position]

        holder.idTv.text = currentItem.idProyecto
        holder.nameTv.text = currentItem.proyecto
        holder.responsibleTv.text = currentItem.responsable
        holder.startDateTv.text = currentItem.fechaInicio
        holder.endDateTv.text = currentItem.fechaFin
        holder.priorityTv.text = currentItem.prioridad
        holder.budgetedProgressTv.text = currentItem.avancePropuesto
        holder.currentProgressTv.text = currentItem.avanceActual
        holder.statusTv.text = currentItem.estatus

    }

    override fun getItemCount() = projectDataList.size

    class AdministracionProyectosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val idTv: TextView = itemView.txt_project_id_value
        val nameTv: TextView = itemView.txt_project_name_value
        val responsibleTv: TextView = itemView.txt_project_responsible_value
        val startDateTv: TextView = itemView.txt_project_start_date_value
        val endDateTv: TextView = itemView.txt_project_end_date_value
        val priorityTv: TextView = itemView.txt_project_priority_value
        val budgetedProgressTv: TextView = itemView.txt_project_budgeted_progress_value
        val currentProgressTv: TextView = itemView.txt_project_current_progress_value
        val statusTv: TextView = itemView.txt_project_status_value

    }

}