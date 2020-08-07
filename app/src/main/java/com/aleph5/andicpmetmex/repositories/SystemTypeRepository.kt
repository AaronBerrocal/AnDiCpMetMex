package com.aleph5.andicpmetmex.repositories

import androidx.lifecycle.LiveData
import com.aleph5.andicpmetmex.daos.SystemTypeDao
import com.aleph5.andicpmetmex.entities.SystemTypeEntity

class SystemTypeRepository private constructor(private val systemTypeDao: SystemTypeDao){

    fun searchSystemTypesByModuleIdRepo(selectedModuleId: String): LiveData<List<SystemTypeEntity>>{
        return systemTypeDao.searchSystemTypesByModuleId(selectedModuleId)
    }

    suspend fun bulkInsertSystemTypesRepo(newSystemTypes: List<SystemTypeEntity>){
        systemTypeDao.bulkInsertSystemTypes(newSystemTypes)
    }

    suspend fun deleteAllSystemTypesRepo(){
        systemTypeDao.deleteAllSystemTypes()
    }

    companion object{
        private var instance: SystemTypeRepository? = null

        fun getInstance(systemTypeDao: SystemTypeDao) =
            instance ?: synchronized(this){
                instance ?: SystemTypeRepository(systemTypeDao).also { instance = it }
            }
    }
}