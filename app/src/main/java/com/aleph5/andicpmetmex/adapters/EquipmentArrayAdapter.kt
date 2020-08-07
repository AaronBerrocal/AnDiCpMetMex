package com.aleph5.andicpmetmex.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.aleph5.andicpmetmex.entities.EquipmentEntity
import java.util.*
import kotlin.collections.ArrayList

class EquipmentArrayAdapter(
    private val mContext: Context,
    private val objects: ArrayList<EquipmentEntity>
): ArrayAdapter<EquipmentEntity?>(mContext, 0, objects as List<EquipmentEntity?>) {

    private var equipmentListFull = ArrayList<EquipmentEntity>(objects)
    private var suggestions = ArrayList<EquipmentEntity>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        var v: View? = convertView
//        if(v == null){
//            v = LayoutInflater.from(mContext).inflate(
//                android.R.layout.simple_list_item_1,
//                parent,
//                false
//            )
//        }
        val v: View = convertView ?: LayoutInflater.from(mContext).inflate(
            android.R.layout.simple_list_item_1,
            parent,
            false
        )

        val equipment: EquipmentEntity? = objects[position]
        if(equipment != null){
            val textViewSignature = v.findViewById<TextView>(android.R.id.text1)
            textViewSignature?.text = equipment.signature
        }

        return v
    }

    override fun getFilter(): Filter {
        return equipmentFilter
    }

    private var equipmentFilter: Filter = object : Filter(){

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            return if(constraint != null){
                suggestions.clear()
                val filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim()
                for(equipment in equipmentListFull){
                    if(equipment.signature.toLowerCase(Locale.getDefault()).contains(filterPattern)){
                        suggestions.add(equipment)
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = suggestions
                filterResults.count = suggestions.size
                filterResults
            }else{
                FilterResults()
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if(results != null && results.count > 0){
                clear()
                addAll((results.values) as ArrayList<EquipmentEntity>)
                notifyDataSetChanged()
            }
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as EquipmentEntity).signature
        }
    }

    fun setEquipments(equipmentList: ArrayList<EquipmentEntity>){
        this.equipmentListFull = equipmentList
        notifyDataSetChanged()
    }
}