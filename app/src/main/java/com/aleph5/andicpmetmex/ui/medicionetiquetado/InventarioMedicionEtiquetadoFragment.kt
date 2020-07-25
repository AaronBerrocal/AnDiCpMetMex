package com.aleph5.andicpmetmex.ui.medicionetiquetado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aleph5.andicpmetmex.R
import com.aleph5.andicpmetmex.adapters.InventarioMedicionEtiquetadoAdapter
import com.aleph5.andicpmetmex.utilityclasses.UtilityMethods
import kotlinx.android.synthetic.main.fragment_inventario_medicion_etiquetado.view.*

class InventarioMedicionEtiquetadoFragment : Fragment() {

//    private lateinit var viewModel: InventarioMedicionEtiquetadoViewModel

    private var measureLabelDataList = UtilityMethods.generateMeasureLabelList(20)
    private var adapter = InventarioMedicionEtiquetadoAdapter(measureLabelDataList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_inventario_medicion_etiquetado, container, false)

        val recyclerView = root.measure_label_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        return root
    }

}