package com.aleph5.andicpmetmex.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.aleph5.andicpmetmex.entities.SubareaEntity
import java.util.*
import kotlin.collections.ArrayList

class SubareaArrayAdapter(
    private val mContext: Context,
    private val objects: ArrayList<SubareaEntity>
): ArrayAdapter<SubareaEntity?>(mContext, 0, objects as List<SubareaEntity?>) {

    private var subareaListFull = ArrayList<SubareaEntity>(objects)
    private var suggestions = ArrayList<SubareaEntity>()

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

        val subarea: SubareaEntity? = objects[position]
        if(subarea != null){
            val textViewSignature = v.findViewById<TextView?>(android.R.id.text1)
            textViewSignature?.text = subarea.signature
        }

        return v
    }

    override fun getFilter(): Filter {
        return subareaFilter
    }

    private var subareaFilter: Filter = object : Filter(){

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            return if(constraint != null){
                suggestions.clear()
                val filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim()
                for(subarea in subareaListFull){
                    if(subarea.signature.toLowerCase(Locale.getDefault()).contains(filterPattern)){
                        suggestions.add(subarea)
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
                addAll((results.values) as ArrayList<SubareaEntity>)
                notifyDataSetChanged()
            }
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as SubareaEntity).signature
        }
    }

    fun setSubareas(subareaList: ArrayList<SubareaEntity>){
        this.subareaListFull = subareaList
        notifyDataSetChanged()
    }
}