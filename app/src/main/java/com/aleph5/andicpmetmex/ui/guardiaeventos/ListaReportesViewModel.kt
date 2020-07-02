package com.aleph5.andicpmetmex.ui.guardiaeventos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListaReportesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is lista reportes Fragment"
    }
    val text: LiveData<String> = _text
}