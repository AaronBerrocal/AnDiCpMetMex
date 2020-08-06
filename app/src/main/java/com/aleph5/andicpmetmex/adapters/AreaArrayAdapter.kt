package com.aleph5.andicpmetmex.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.aleph5.andicpmetmex.entities.AreaEntity
import com.aleph5.andicpmetmex.entities.PlantEntity
import java.util.*
import kotlin.collections.ArrayList

class AreaArrayAdapter(
    private val mContext: Context,
    private val objects: ArrayList<AreaEntity>
) : ArrayAdapter<AreaEntity?>(mContext, 0, objects as List<AreaEntity?>) {

    private var areaListFull = ArrayList<AreaEntity>(objects) //OR objects.clone() as ArrayList<AreaEntity>
    private var suggestions = ArrayList<AreaEntity>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v: View? = convertView
        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(
                android.R.layout.simple_list_item_1,
                parent,
                false
            )
        }

        val area: AreaEntity? = objects[position]
        if(area != null){
            val textViewSignature = v?.findViewById(android.R.id.text1) as TextView?
            textViewSignature?.text = area.signature
        }

        return v!!
    }

    override fun getFilter(): Filter {
        return areaFilter
    }

    private var areaFilter: Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            return if(constraint != null){
                suggestions.clear()
                val filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim()
                for(area in areaListFull){
                    if(area.signature.toLowerCase(Locale.getDefault()).contains(filterPattern)){
                        suggestions.add(area)
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
            if (results != null && results.count > 0) {
                clear()
                addAll((results.values) as ArrayList<AreaEntity>)
                notifyDataSetChanged()
            }
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as AreaEntity).signature
        }
    }

    fun setAreas(areaList: ArrayList<AreaEntity>){
        this.areaListFull = areaList
        notifyDataSetChanged()
    }
}