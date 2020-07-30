package com.aleph5.andicpmetmex.ui.eventos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleph5.andicpmetmex.database.AnDiCpRoomDatabase
import com.aleph5.andicpmetmex.entities.EventEntity
import com.aleph5.andicpmetmex.repositories.AnDiCpRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AdministracionEventosViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AnDiCpRepository
    val allEvents: LiveData<List<EventEntity>>

    init {
        val eventDao = AnDiCpRoomDatabase.getDatabase(application, viewModelScope).EventDao()
        repository = AnDiCpRepository(eventDao)
        allEvents = repository.events
    }

    fun insertEventVm(newEvent: EventEntity) : Job = viewModelScope.launch {
        repository.insertEventRepo(newEvent)
    }

    fun deleteAllVm(): Job = viewModelScope.launch {
        repository.deleteAllEventsRepo()
    }

//    fun insertEventVm(newEvent: EventEntity) = viewModelScope.launch(Dispatchers.IO) {
//        repository.insertEventRepo(newEvent)
//    }

}