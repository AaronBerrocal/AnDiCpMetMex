package com.aleph5.andicpmetmex.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.aleph5.andicpmetmex.entities.PriorityEntity
import com.aleph5.andicpmetmex.entities.SystemTypeEntity

class PriorityArrayAdapter(
    private val mContext: Context,
    private var objects: ArrayList<PriorityEntity>
): ArrayAdapter<PriorityEntity?>(mContext, 0, objects as List<PriorityEntity>) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View{
        val view = convertView ?: LayoutInflater.from(mContext).inflate(
            android.R.layout.simple_spinner_item,
            parent,
            false
        )

        val priority: PriorityEntity? = getItem(position)
        if(priority != null){
            val textViewSignature = view.findViewById<TextView>(android.R.id.text1)
            textViewSignature?.text = priority.signature
        }

        return view
    }

    fun setPriorities(priorityList: ArrayList<PriorityEntity>){
        clear()
        addAll(priorityList)
        notifyDataSetChanged()
    }
}