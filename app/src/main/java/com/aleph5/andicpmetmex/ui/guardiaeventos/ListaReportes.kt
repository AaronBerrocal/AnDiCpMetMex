package com.aleph5.andicpmetmex.ui.guardiaeventos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aleph5.andicpmetmex.R

class ListaReportes : Fragment() {

    private lateinit var listaReportesViewModel: ListaReportesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listaReportesViewModel =
            ViewModelProvider(this).get(ListaReportesViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_lista_reportes, container, false)
//        val textView: TextView = root.findViewById(R.id.text_list_reportes)
//
//        listaReportesViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}