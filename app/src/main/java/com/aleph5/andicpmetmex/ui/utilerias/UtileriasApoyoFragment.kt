package com.aleph5.andicpmetmex.ui.utilerias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleph5.andicpmetmex.R
import com.aleph5.andicpmetmex.adapters.UtileriasApoyoAdapter
import com.aleph5.andicpmetmex.utilityclasses.UtilityMethods
import kotlinx.android.synthetic.main.fragment_utilerias_apoyo.view.*

class UtileriasApoyoFragment : Fragment() {

//    private lateinit var viewModel: UtileriasApoyoViewModel

    private var utilitiesDataList = UtilityMethods.generateUtilityList(7)
    private var adapter = UtileriasApoyoAdapter(utilitiesDataList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_utilerias_apoyo, container, false)

        val recyclerView: RecyclerView = root.utilities_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        return root
    }

}