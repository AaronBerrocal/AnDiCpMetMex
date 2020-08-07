package com.aleph5.andicpmetmex.repositories

import androidx.lifecycle.LiveData
import com.aleph5.andicpmetmex.daos.PriorityDao
import com.aleph5.andicpmetmex.entities.PriorityEntity

class PriorityRepository private constructor(private val priorityDao: PriorityDao) {

    val priorities: LiveData<List<PriorityEntity>> = priorityDao.loadAllPriorities()

    suspend fun bulkInsertPrioritiesRepo(newPriorities: List<PriorityEntity>){
        priorityDao.bulkInsertPriorities(newPriorities)
    }

    suspend fun deleteAllPrioritiesRepo(){
        priorityDao.deleteAllPriorities()
    }

    companion object{
        @Volatile
        private var instance: PriorityRepository? = null

        fun getInstance(priorityDao: PriorityDao) =
            instance ?: synchronized(this){
                instance ?: PriorityRepository(priorityDao).also { instance = it }
            }
    }

}