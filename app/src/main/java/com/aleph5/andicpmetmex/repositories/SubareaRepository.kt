package com.aleph5.andicpmetmex.repositories

import androidx.lifecycle.LiveData
import com.aleph5.andicpmetmex.daos.SubareaDao
import com.aleph5.andicpmetmex.entities.SubareaEntity

class SubareaRepository private constructor(private val subareaDao: SubareaDao) {

    val subareas: LiveData<List<SubareaEntity>> = subareaDao.loadAllSubareas()

    suspend fun bulkInsertSubareasRepo(newSubareas: List<SubareaEntity>){
        subareaDao.bulkInsertSubareas(newSubareas)
    }

    suspend fun deleteAllSubareasRepo(){
        subareaDao.deleteAllSubareas()
    }

    companion object{
        private var instance: SubareaRepository? = null

        fun getInstance(subareaDao: SubareaDao) =
            instance ?: synchronized(this){
                instance ?: SubareaRepository(subareaDao).also { instance = it }
            }
    }
}