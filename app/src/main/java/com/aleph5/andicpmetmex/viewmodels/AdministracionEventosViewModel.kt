package com.aleph5.andicpmetmex.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleph5.andicpmetmex.entities.EventEntity
import com.aleph5.andicpmetmex.repositories.EventRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AdministracionEventosViewModel(private val eventRepository: EventRepository) : ViewModel() {

    val allEvents = eventRepository.events
    val eventsCount = eventRepository.eventsCount

    fun insertEventVm(newEvent: EventEntity){
        viewModelScope.launch {
            eventRepository.insertEventRepo(newEvent)
        }
    }

    fun deleteAllVm(){
        viewModelScope.launch {
            eventRepository.deleteAllEventsRepo()
        }
    }

}