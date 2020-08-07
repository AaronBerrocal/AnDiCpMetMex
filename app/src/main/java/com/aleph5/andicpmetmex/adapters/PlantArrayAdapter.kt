package com.aleph5.andicpmetmex.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.aleph5.andicpmetmex.entities.PlantEntity
import java.util.*
import kotlin.collections.ArrayList

class PlantArrayAdapter(
    private val mContext: Context,
    private val objects: ArrayList<PlantEntity>
) : ArrayAdapter<PlantEntity?>(mContext, 0, objects as List<PlantEntity?>) {

    private var plantListFull = ArrayList<PlantEntity>(objects) //objects.clone() as ArrayList<PlantEntity>
    private var suggestions = ArrayList<PlantEntity>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        var v: View? = convertView
//        if (v == null) {
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

        val plant: PlantEntity? = objects[position]
        if(plant != null){
            val textViewSignature = v.findViewById(android.R.id.text1) as TextView?
            textViewSignature?.text = plant.signature
        }

        return v
    }

    override fun getFilter(): Filter {
        return plantFilter
    }

    private var plantFilter: Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            return if(constraint != null) {
                suggestions.clear()
                val filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim()
                for (plant in plantListFull) {
                    if (plant.signature.toLowerCase(Locale.getDefault()).contains(filterPattern)) {
                        suggestions.add(plant)
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
                addAll((results.values) as ArrayList<PlantEntity>)
                notifyDataSetChanged()
            }
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as PlantEntity).signature
        }
    }

    fun setPlants(plantList: ArrayList<PlantEntity>){
        this.plantListFull = plantList
        notifyDataSetChanged()
    }
}