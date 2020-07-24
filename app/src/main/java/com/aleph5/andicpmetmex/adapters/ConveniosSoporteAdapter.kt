package com.aleph5.andicpmetmex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.aleph5.andicpmetmex.R
import com.aleph5.andicpmetmex.datamodels.AgreementData
import kotlinx.android.synthetic.main.item_agreement.view.*

class ConveniosSoporteAdapter(private val agreementDataList: ArrayList<AgreementData>) : Adapter<ConveniosSoporteAdapter.ConveniosSoporteViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConveniosSoporteViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_agreement,
            parent,
            false
        )

        return ConveniosSoporteViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ConveniosSoporteViewHolder,
        position: Int
    ) {

        val currentItem = agreementDataList[position]

        holder.providerTv.text = currentItem.proveedor
        holder.agreementTv.text = currentItem.numeroContrato
        holder.contactSupportTv.text = currentItem.numeroSoporte
        holder.startDateTv.text = currentItem.fechaInicio
        holder.endDateTv.text = currentItem.fechaFin
        holder.validityTv.text = "${currentItem.vigencia} meses"
        holder.statusTv.text = currentItem.estatus
    }

    override fun getItemCount() = agreementDataList.size

    class ConveniosSoporteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val providerTv : TextView = itemView.txt_agreement_provider_value
        val agreementTv : TextView = itemView.txt_agreement_number_value
        val contactSupportTv : TextView = itemView.txt_agreement_contact_support_value
        val startDateTv : TextView = itemView.txt_agreement_start_date_value
        val endDateTv : TextView = itemView.txt_agreement_end_date_value
        val validityTv : TextView = itemView.txt_agreement_validity_value
        val statusTv : TextView = itemView.txt_agreement_status_value
        val documentLinkIb : ImageButton = itemView.btn_agreement_document
        val webPageLinkIb : ImageButton = itemView.btn_agreement_web_page
    }
}