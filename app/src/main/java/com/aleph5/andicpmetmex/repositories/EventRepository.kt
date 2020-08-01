package com.aleph5.andicpmetmex.repositories

import androidx.lifecycle.LiveData
import com.aleph5.andicpmetmex.daos.EventDao
import com.aleph5.andicpmetmex.entities.EventEntity

class EventRepository private constructor(private val eventDao: EventDao){

    val events: LiveData<List<EventEntity>> = eventDao.loadAllEvents()

    val eventsCount: LiveData<Int> = eventDao.countEvents()

    suspend fun insertEventRepo(newEvent: EventEntity){
        eventDao.insertEvent(newEvent)
    }

    suspend fun deleteAllEventsRepo(){
        eventDao.deleteAllEvents()
    }

    companion object{
        @Volatile
        private var instance: EventRepository? = null

        fun getInstance(eventDao: EventDao) =
            instance ?: synchronized(this){
                instance ?: EventRepository(eventDao).also { instance = it }
            }
    }
}