package com.aleph5.andicpmetmex.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aleph5.andicpmetmex.repositories.*

class AdministracionEventosViewModelFactory(
    private val eventRepository: EventRepository,
    private val plantRepository: PlantRepository,
    private var areaRepository: AreaRepository,
    private var subareaRepository: SubareaRepository,
    private var equipmentRepository: EquipmentRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return AdministracionEventosViewModel(
            eventRepository,
            plantRepository,
            areaRepository,
            subareaRepository,
            equipmentRepository
        ) as T
    }
}