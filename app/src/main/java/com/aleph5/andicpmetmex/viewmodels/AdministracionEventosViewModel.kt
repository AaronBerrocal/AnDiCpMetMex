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
    private val systemTypeRepository: SystemTypeRepository
) : ViewModel() {

    val allEvents = eventRepository.events
    val eventsCount = eventRepository.eventsCount
    val allPlants = plantRepository.plants
    val allAreas = areaRepository.areas
    val allSubareaSignatures = subareaRepository.subareaSignatures
    val allEquipmentSignatures = equipmentRepository.equipmentSignatures

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

    fun searchAreaSignaturesByPlantIdVm(selectedPlantId: String) =
        areaRepository.searchAreaSignaturesByPlantIdRepo(selectedPlantId)

    fun bulkInsertAreasVm(newAreas: List<AreaEntity>): Job = viewModelScope.launch {
        areaRepository.bulkInsertAreasRepo(newAreas)
    }

    fun deleteAllAreasVm(): Job = viewModelScope.launch {
        areaRepository.deleteAllAreasRepo()
    }
    //endregion Area Methods

    //region Subarea Methods
    suspend fun searchSubareaSignaturesByAreaIdVm(selectedAreaId: String): LiveData<List<String>>{
        return withContext(viewModelScope.coroutineContext) {
            subareaRepository.searchSubareaSignaturesByAreaIdRepo(selectedAreaId)
        }
    }

    fun bulkInsertSubareasVm(newSubareas: List<SubareaEntity>): Job = viewModelScope.launch {
        subareaRepository.bulkInsertSubareasRepo(newSubareas)
    }

    fun deleteAllSubareasVm(): Job = viewModelScope.launch {
        subareaRepository.deleteAllSubareasRepo()
    }
    //endregion Subarea Methods

    //region Equipment Methods
    suspend fun searchEquipmentSignaturesBySubareaIdVm(selectedSubareaId: String): LiveData<List<String>>{
        return withContext(viewModelScope.coroutineContext) {
            equipmentRepository.searchEquipmentSignaturesBySubareaIdRepo(selectedSubareaId)
        }
    }

    fun bulkInsertEquipmentVm(newEquipment: List<EquipmentEntity>): Job = viewModelScope.launch {
        equipmentRepository.bulkInsertEquipmentRepo(newEquipment)
    }

    fun deleteAllEquipmentVm(): Job = viewModelScope.launch {
        equipmentRepository.deleteAllEquipmentRepo()
    }
    //endregion Equipment Methods

    //region SystemType Methods
    suspend fun searchSystemTypesByModuleIdVm(selectedModuleId: String): LiveData<List<String>>{
        return withContext(viewModelScope.coroutineContext){
            systemTypeRepository.searchSystemTypesByModuleIdRepo(selectedModuleId)
        }
    }

    fun bulkInsertSystemTypesVm(newSystemTypes: List<SystemTypeEntity>): Job = viewModelScope.launch {
        systemTypeRepository.bulkInsertSystemTypesRepo(newSystemTypes)
    }

    fun deleteAllSystemTypesVm(): Job = viewModelScope.launch {
        equipmentRepository.deleteAllEquipmentRepo()
    }
    //endregion SystemType Methods

}