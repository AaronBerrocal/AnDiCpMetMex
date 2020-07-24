package com.aleph5.andicpmetmex.ui.convenios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleph5.andicpmetmex.R
import com.aleph5.andicpmetmex.adapters.ConveniosSoporteAdapter
import com.aleph5.andicpmetmex.utilityclasses.UtilityMethods
import kotlinx.android.synthetic.main.fragment_convenios_soporte.view.*

class ConveniosSoporteFragment : Fragment() {

//    private lateinit var viewModel: ConveniosSoporteViewModel

    private var agreementDataList = UtilityMethods.generateAgreementList(12)
    private var adapter = ConveniosSoporteAdapter(agreementDataList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_convenios_soporte, container, false)

        val recyclerView : RecyclerView = root.agreements_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        return root
    }

}