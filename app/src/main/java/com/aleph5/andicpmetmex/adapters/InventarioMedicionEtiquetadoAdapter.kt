package com.aleph5.andicpmetmex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.aleph5.andicpmetmex.R
import com.aleph5.andicpmetmex.datamodels.MeasureLabelData
import kotlinx.android.synthetic.main.item_measure_label.view.*
import org.w3c.dom.Text

class InventarioMedicionEtiquetadoAdapter(private val MeasureLabelDataList: ArrayList<MeasureLabelData>) : Adapter<InventarioMedicionEtiquetadoAdapter.InventarioMedicionEtiquetadoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InventarioMedicionEtiquetadoViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_measure_label,
            parent,
            false
        )

        return InventarioMedicionEtiquetadoViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: InventarioMedicionEtiquetadoViewHolder,
        position: Int
    ) {

        val currentItem = MeasureLabelDataList[position]

        holder.serialNumberTv.text = currentItem.numeroSerie
        holder.equipmentNameTv.text = currentItem.equipo
        holder.brandTv.text = currentItem.marca
        holder.equipmentTypeTv.text = currentItem.tipoEquipo
        holder.statusTv.text = currentItem.estatus
        holder.activeIv.setImageResource(
            when(currentItem.activo){
                0 -> R.drawable.ic_baseline_check_circle_invalid_18
                1 -> R.drawable.ic_baseline_check_circle_18
                else -> R.drawable.ic_baseline_check_circle_invalid_18
            }
        )
        holder.acquisitionDateTv.text = currentItem.fechaAdquiision
        holder.warrantyExpiryDateTv.text = currentItem.vigenciaGarantia

        holder.statusTv.setTextColor(
            ContextCompat.getColor(
                holder.itemView.context,
                when(currentItem.estatus){
                    "Disponible" -> R.color.colorGreenCheck
                    "Reparación" -> R.color.colorYellowCheck
                    "Baja" -> R.color.colorRedCheck
                    else -> R.color.colorRedCheck
                }
            )
        )

    }

    override fun getItemCount() = MeasureLabelDataList.size

    class InventarioMedicionEtiquetadoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val serialNumberTv: TextView = itemView.txt_measure_serial_value
        val equipmentNameTv: TextView = itemView.txt_measure_name_value
        val brandTv: TextView = itemView.txt_measure_brand_value
        val equipmentTypeTv: TextView = itemView.txt_measure_equipment_type_value
        val statusTv: TextView = itemView.txt_measure_status_value
        val activeIv: ImageView = itemView.img_measure_active_value
        val acquisitionDateTv: TextView = itemView.txt_measure_acquisition_date_value
        val warrantyExpiryDateTv: TextView = itemView.txt_measure_warranty_date_value
    }


}