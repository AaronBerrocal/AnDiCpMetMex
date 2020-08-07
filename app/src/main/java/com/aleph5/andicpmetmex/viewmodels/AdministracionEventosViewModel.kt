package com.aleph5.andicpmetmex.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleph5.andicpmetmex.entities.*
import com.aleph5.andicpmetmex.repositories.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class AdministracionEventosViewModel(
    private val eventRepository: EventRepository,
    private val plantRepository: PlantRepository,
    private val areaRepository: AreaRepository,
    private val subareaRepository: SubareaRepository,
    private val equipmentRepository: EquipmentRepository,
    private val systemTypeRepository: SystemTypeRepository,
    private val eventTypeRepository: EventTypeRepository,
    private val priorityRepository: PriorityRepository,
    private val statusRepository: StatusRepository
) : ViewModel() {

    val allEvents = eventRepository.events
    val eventsCount = eventRepository.eventsCount
    val allPlants = plantRepository.plants
    val allAreas = areaRepository.areas
    val allSubareas = subareaRepository.subareas
    val allEquipments = equipmentRepository.equipments
    val allEventTypes = eventTypeRepository.eventTypes
    val allPriorities = priorityRepository.priorities

    //region Event methods
    fun insertEventVm(newEvent: EventEntity){
        viewModelScope.launch {
            eventRepository.insertEventRepo(newEvent)
        }
    }

    fun deleteAllEventsVm(){
        viewModelScope.launch {
            eventRepository.deleteAllEventsRepo()
        }
    }
    //endregion Event methods

    //region Plant Methods
    fun bulkInsertPlantsVm(newPlants: List<PlantEntity>): Job = viewModelScope.launch {
        plantRepository.bulkInsertPlantsRepo(newPlants)
    }

    fun deleteAllPlantsVm(): Job = viewModelScope.launch {
        plantRepository.deleteAllPlantsRepo()
    }
    //endregion Plant Methods

    //region Area Methods
    fun bulkInsertAreasVm(newAreas: List<AreaEntity>): Job = viewModelScope.launch {
        areaRepository.bulkInsertAreasRepo(newAreas)
    }

    fun deleteAllAreasVm(): Job = viewModelScope.launch {
        areaRepository.deleteAllAreasRepo()
    }
    //endregion Area Methods

    //region Subarea Methods
    fun bulkInsertSubareasVm(newSubareas: List<SubareaEntity>): Job = viewModelScope.launch {
        subareaRepository.bulkInsertSubareasRepo(newSubareas)
    }

    fun deleteAllSubareasVm(): Job = viewModelScope.launch {
        subareaRepository.deleteAllSubareasRepo()
    }
    //endregion Subarea Methods

    //region Equipment Methods
    fun bulkInsertEquipmentVm(newEquipment: List<EquipmentEntity>): Job = viewModelScope.launch {
        equipmentRepository.bulkInsertEquipmentRepo(newEquipment)
    }

    fun deleteAllEquipmentVm(): Job = viewModelScope.launch {
        equipmentRepository.deleteAllEquipmentRepo()
    }
    //endregion Equipment Methods

    //region SystemType Methods
    fun searchSystemTypesByModuleIdVm(selectedModuleId: String): LiveData<List<SystemTypeEntity>>{
        return systemTypeRepository.searchSystemTypesByModuleIdRepo(selectedModuleId)
    }

    fun bulkInsertSystemTypesVm(newSystemTypes: List<SystemTypeEntity>): Job = viewModelScope.launch {
        systemTypeRepository.bulkInsertSystemTypesRepo(newSystemTypes)
    }

    fun deleteAllSystemTypesVm(): Job = viewModelScope.launch {
        equipmentRepository.deleteAllEquipmentRepo()
    }
    //endregion SystemType Methods

    //region EventType Methods
    fun bulkInsertEventTypesVm(newEventTypes: List<EventTypeEntity>): Job = viewModelScope.launch {
        eventTypeRepository.bulkInsertEventTypesRepo(newEventTypes)
    }

    fun deleteAllEventTypesVm(): Job = viewModelScope.launch {
        eventTypeRepository.deleteAllEventTypesRepo()
    }
    //endregion EventType Methods

    //region Priority Methods
    fun bulkInsertPrioritiesVm(newPriorities: List<PriorityEntity>): Job = viewModelScope.launch {
        priorityRepository.bulkInsertPrioritiesRepo(newPriorities)
    }

    fun deleteAllPrioritiesVm(): Job = viewModelScope.launch {
        priorityRepository.deleteAllPrioritiesRepo()
    }
    //endregion Priority Methods

    //region Status Methods
    fun searchStatusByModuleIdVm(selectedModuleId: String): LiveData<List<StatusEntity>>{
        return statusRepository.searchStatusByModuleIdRepo(selectedModuleId)
    }

    fun bulkInsertStatusVm(newStatus: List<StatusEntity>): Job = viewModelScope.launch {
        statusRepository.bulkInsertStatusRepo(newStatus)
    }

    fun deleteAllStatusVm(): Job = viewModelScope.launch {
        statusRepository.deleteAllStatusRepo()
    }
    //endregion Status Methods

}