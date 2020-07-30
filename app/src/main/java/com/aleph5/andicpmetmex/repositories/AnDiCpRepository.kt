package com.aleph5.andicpmetmex.repositories

import androidx.lifecycle.LiveData
import com.aleph5.andicpmetmex.daos.EventDao
import com.aleph5.andicpmetmex.entities.EventEntity

class AnDiCpRepository(
    private val eventDao: EventDao
) {

    val events: LiveData<List<EventEntity>> = eventDao.loadAllEvents()

    suspend fun insertEventRepo(newEvent: EventEntity){
        eventDao.insertEvent(newEvent)
    }

    suspend fun deleteAllEventsRepo(){
        eventDao.deleteAllEvents()
    }

}