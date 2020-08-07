package com.aleph5.andicpmetmex.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aleph5.andicpmetmex.repositories.*

class AdministracionEventosViewModelFactory(
    private val eventRepository: EventRepository,
    private val plantRepository: PlantRepository,
    private var areaRepository: AreaRepository,
    private var subareaRepository: SubareaRepository,
    private var equipmentRepository: EquipmentRepository,
    private var systemTypeRepository: SystemTypeRepository,
    private var eventTypeRepository: EventTypeRepository,
    private var priorityRepository: PriorityRepository,
    private var statusRepository: StatusRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return AdministracionEventosViewModel(
            eventRepository,
            plantRepository,
            areaRepository,
            subareaRepository,
            equipmentRepository,
            systemTypeRepository,
            eventTypeRepository,
            priorityRepository,
            statusRepository
        ) as T
    }
}