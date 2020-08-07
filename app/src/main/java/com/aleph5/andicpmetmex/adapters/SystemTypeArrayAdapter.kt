package com.aleph5.andicpmetmex.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.aleph5.andicpmetmex.entities.SubareaEntity
import com.aleph5.andicpmetmex.entities.SystemTypeEntity

class SystemTypeArrayAdapter(
    private val mContext: Context,
    private var objects: ArrayList<SystemTypeEntity>
): ArrayAdapter<SystemTypeEntity?>(mContext, 0, objects as List<SystemTypeEntity>) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View{
//        val view = convertView ?: if(isDropdown) {
//                LayoutInflater.from(mContext).inflate(
//                    android.R.layout.simple_spinner_dropdown_item,
//                    parent,
//                    false
//                )
//            }else {
//                LayoutInflater.from(mContext).inflate(
//                    android.R.layout.simple_spinner_item,
//                    parent,
//                    false
//                )
//            }
        val view = convertView ?: LayoutInflater.from(mContext).inflate(
                    android.R.layout.simple_spinner_item,
                    parent,
                    false
                )

        val systemType: SystemTypeEntity? = getItem(position)
        if(systemType != null){
            val textViewSignature = view.findViewById<TextView>(android.R.id.text1)
            textViewSignature?.text = systemType.signature
        }

        return view
    }

    fun setSystemTypes(systemTypeList: ArrayList<SystemTypeEntity>){
        clear()
        addAll(systemTypeList)
        notifyDataSetChanged()
    }
}