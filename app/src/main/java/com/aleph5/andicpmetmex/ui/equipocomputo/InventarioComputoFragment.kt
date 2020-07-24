package com.aleph5.andicpmetmex.ui.equipocomputo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleph5.andicpmetmex.R
import com.aleph5.andicpmetmex.adapters.InventarioComputoAdapter
import com.aleph5.andicpmetmex.datamodels.ComputingEquipmentData
import com.aleph5.andicpmetmex.utilityclasses.UtilityMethods
import kotlinx.android.synthetic.main.fragment_inventario_computo.view.*

class InventarioComputoFragment : Fragment() {

//    private lateinit var viewModel: InventarioComputoViewModel

    private var computingEquipmentDataList = UtilityMethods.generateComputingEquipmentList(15)
    private var adapter = InventarioComputoAdapter(computingEquipmentDataList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_inventario_computo, container, false)

        val recyclerView: RecyclerView = root.equipment_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        return root
    }

}