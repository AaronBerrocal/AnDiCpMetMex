package com.aleph5.andicpmetmex.ui.proyectos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleph5.andicpmetmex.R
import com.aleph5.andicpmetmex.adapters.AdministracionProyectosAdapter
import com.aleph5.andicpmetmex.utilityclasses.UtilityMethods
import kotlinx.android.synthetic.main.fragment_administracion_proyectos.view.*

class AdministracionProyectosFragment : Fragment() {

//    private lateinit var viewModel: AdministracionProyectosViewModel

    private var projectDataList = UtilityMethods.generateProjectList(20)
    private var adapter = AdministracionProyectosAdapter(projectDataList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_administracion_proyectos, container, false)

        val recyclerView: RecyclerView = root.project_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        return root
    }

}