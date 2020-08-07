package com.aleph5.andicpmetmex.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.aleph5.andicpmetmex.entities.EventTypeEntity
import com.aleph5.andicpmetmex.entities.SystemTypeEntity
import java.util.*
import kotlin.collections.ArrayList

class EventTypeArrayAdapter(
    private val mContext: Context,
    private var objects: ArrayList<EventTypeEntity>
): ArrayAdapter<EventTypeEntity?>(mContext, 0, objects as List<EventTypeEntity>) {

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

        val eventType: EventTypeEntity? = getItem(position)
        if(eventType != null){
            val textViewSignature = view.findViewById<TextView>(android.R.id.text1)
            textViewSignature?.text = eventType.signature
        }

        return view
    }

    fun setEventTypes(eventTypesList: ArrayList<EventTypeEntity>){
        clear()
        addAll(eventTypesList)
        notifyDataSetChanged()
    }
}