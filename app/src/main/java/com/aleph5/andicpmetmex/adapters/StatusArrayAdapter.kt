package com.aleph5.andicpmetmex.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.aleph5.andicpmetmex.entities.StatusEntity
import com.aleph5.andicpmetmex.entities.SystemTypeEntity
import java.util.*
import kotlin.collections.ArrayList

class StatusArrayAdapter(
    private val mContext: Context,
    private var objects: ArrayList<StatusEntity>
): ArrayAdapter<StatusEntity?>(mContext, 0, objects as List<StatusEntity>) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(mContext).inflate(
            android.R.layout.simple_spinner_item,
            parent,
            false
        )

        val status: StatusEntity? = getItem(position)
        if(status != null){
            val textViewSignature = view.findViewById<TextView>(android.R.id.text1)
            textViewSignature?.text = status.signature
        }

        return view
    }

    fun setStatus(statusList: ArrayList<StatusEntity>){
        clear()
        addAll(statusList)
        notifyDataSetChanged()
    }
}