package com.aleph5.andicpmetmex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.aleph5.andicpmetmex.R
import com.aleph5.andicpmetmex.datamodels.UtilityData
import kotlinx.android.synthetic.main.item_utility.view.*

class UtileriasApoyoAdapter(private val utilityDataList: ArrayList<UtilityData>) : Adapter<UtileriasApoyoAdapter.UtileriasApoyoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UtileriasApoyoViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_utility,
            parent,
            false
        )

        return UtileriasApoyoViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: UtileriasApoyoViewHolder,
        position: Int
    ) {

        val currentItem = utilityDataList[position]

        holder.providerTv.text = currentItem.proveedor
        holder.utilityTv.text = currentItem.utileria
        holder.ipTv.text = currentItem.ipAcceso
        holder.activeIv.setImageResource(
            when(currentItem.activo){
                0 -> R.drawable.ic_baseline_check_circle_invalid_18
                1 -> R.drawable.ic_baseline_check_circle_18
                else -> R.drawable.ic_baseline_check_circle_invalid_18
            }
        )


    }

    override fun getItemCount() = utilityDataList.size

    class UtileriasApoyoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val providerTv: TextView = itemView.txt_utility_provider_value
        val utilityTv: TextView = itemView.txt_utility_name_value
        val ipTv: TextView = itemView.txt_utility_acces_ip_value
        val activeIv: ImageView = itemView.img_utility_active_value
        val userManualIb: ImageButton = itemView.btn_utility_user_manual
        val instructionsManualIb: ImageButton = itemView.btn_utility_instructions_manual
        val mobileAppLinkIb: ImageButton = itemView.btn_utility_mobile_app_link
        val webPageLinkIb: ImageButton = itemView.btn_utility_web_page_link
    }
}