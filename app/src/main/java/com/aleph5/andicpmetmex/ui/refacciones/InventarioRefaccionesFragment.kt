package com.aleph5.andicpmetmex.ui.refacciones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleph5.andicpmetmex.R
import com.aleph5.andicpmetmex.adapters.InventarioRefaccionesAdapter
import com.aleph5.andicpmetmex.utilityclasses.UtilityMethods
import kotlinx.android.synthetic.main.fragment_inventario_refacciones.view.*

class InventarioRefaccionesFragment : Fragment() {

//    private lateinit var viewModel: InventarioRefaccionesViewModel

    private var partDataList = UtilityMethods.generatePartList(20)
    private var adapter = InventarioRefaccionesAdapter(partDataList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_inventario_refacciones, container, false)

        val recyclerView: RecyclerView = root.parts_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        return root
    }

}