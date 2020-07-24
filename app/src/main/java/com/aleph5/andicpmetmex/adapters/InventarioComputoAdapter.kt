package com.aleph5.andicpmetmex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.aleph5.andicpmetmex.R
import com.aleph5.andicpmetmex.datamodels.ComputingEquipmentData
import kotlinx.android.synthetic.main.item_computing_equipment.view.*

class InventarioComputoAdapter(private val computingEquipmentDataList : ArrayList<ComputingEquipmentData>) : Adapter<InventarioComputoAdapter.InventarioComputoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InventarioComputoViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_computing_equipment,
            parent,
            false
        )

        return InventarioComputoViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: InventarioComputoViewHolder,
        position: Int
    ) {

        val currentItem = computingEquipmentDataList[position]

        holder.equipmentTypeTv.text = currentItem.tipoEquipo
        holder.serviceTagTv.text = currentItem.serviceTag
        holder.providerTv.text = currentItem.proveedor
        holder.modeTv.text = currentItem.modalidad
        holder.equipmentStatusTv.text = currentItem.estatusEquipo
        holder.monthFeeTv.text = "${currentItem.rentaMensualEquipo} usd"
        holder.equipmentExpiryTv.text = currentItem.fechaVencimientoGarantiaEquipo
        holder.annexTv.text = currentItem.anexo
        holder.annexStatusTv.text = currentItem.estatusAnexo
        holder.annexExpiryTv.text = currentItem.fechaVencimientoAnexo

        holder.equipmentStatusTv.setTextColor(
            ContextCompat.getColor(
                holder.itemView.context,
                when(currentItem.estatusEquipo){
                    "Uso" -> R.color.colorGreenCheck
                    "Stock" -> R.color.colorBlueCheck
                    "Reparación" -> R.color.colorYellowCheck
                    "Baja" -> R.color.colorRedCheck
                    else -> R.color.colorRedCheck
                }
            )
        )

    }

    override fun getItemCount() = computingEquipmentDataList.size

    class InventarioComputoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val equipmentTypeTv: TextView = itemView.txt_equipment_type_value
        val serviceTagTv: TextView = itemView.txt_equipment_service_tag_value
        val providerTv: TextView = itemView.txt_equipment_provider_value
        val modeTv: TextView = itemView.txt_equipment_mode_value
        val equipmentStatusTv: TextView = itemView.txt_equipment_status_value
        val monthFeeTv: TextView = itemView.txt_equipment_month_fee_value
        val equipmentExpiryTv: TextView = itemView.txt_equipment_expiry_date_value
        val annexTv: TextView = itemView.txt_equipment_annex_value
        val annexStatusTv: TextView = itemView.txt_equipment_annex_status_value
        val annexExpiryTv: TextView = itemView.txt_equipment_annex_expiry_date_value

    }

}