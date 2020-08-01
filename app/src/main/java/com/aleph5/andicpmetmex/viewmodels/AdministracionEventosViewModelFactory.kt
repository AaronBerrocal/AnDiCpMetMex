package com.aleph5.andicpmetmex.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aleph5.andicpmetmex.repositories.EventRepository

class AdministracionEventosViewModelFactory(
    private val eventRepository: EventRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return AdministracionEventosViewModel(eventRepository) as T
    }
}