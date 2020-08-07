package com.aleph5.andicpmetmex.repositories

import androidx.lifecycle.LiveData
import com.aleph5.andicpmetmex.daos.StatusDao
import com.aleph5.andicpmetmex.entities.StatusEntity

class StatusRepository private constructor(private val statusDao: StatusDao){

    fun searchStatusByModuleIdRepo(selectedModuleId: String): LiveData<List<StatusEntity>>{
        return statusDao.searchStatusByModuleId(selectedModuleId)
    }

    suspend fun bulkInsertStatusRepo(newStatus: List<StatusEntity>){
        statusDao.bulkInsertStatus(newStatus)
    }

    suspend fun deleteAllStatusRepo(){
        statusDao.deleteAllStatus()
    }

    companion object{
        @Volatile
        private var instance: StatusRepository? = null

        fun getInstance(statusDao: StatusDao) =
            instance ?: synchronized(this){
                instance ?: StatusRepository(statusDao).also { instance = it }
            }
    }

}