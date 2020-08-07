package com.aleph5.andicpmetmex.repositories

import androidx.lifecycle.LiveData
import com.aleph5.andicpmetmex.daos.EventTypeDao
import com.aleph5.andicpmetmex.entities.EventTypeEntity

class EventTypeRepository private constructor(private val eventTypeDao: EventTypeDao){

    val eventTypes: LiveData<List<EventTypeEntity>> = eventTypeDao.loadAllEventTypes()

    suspend fun bulkInsertEventTypesRepo(newEventTypes: List<EventTypeEntity>){
        eventTypeDao.bulkInsertEventTypes(newEventTypes)
    }

    suspend fun deleteAllEventTypesRepo(){
        eventTypeDao.deleteAllEventTypes()
    }

    companion object{
        @Volatile
        private var instance: EventTypeRepository? = null

        fun getInstance(eventTypeDao: EventTypeDao) =
            instance ?: synchronized(this){
                instance ?: EventTypeRepository(eventTypeDao).also { instance = it }
            }
    }
}