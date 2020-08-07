package com.aleph5.andicpmetmex.repositories

import androidx.lifecycle.LiveData
import com.aleph5.andicpmetmex.daos.AreaDao
import com.aleph5.andicpmetmex.entities.AreaEntity

class AreaRepository private constructor(private val areaDao: AreaDao){

    val areas: LiveData<List<AreaEntity>> = areaDao.loadAllAreas()

    suspend fun bulkInsertAreasRepo(newAreas: List<AreaEntity>){
        areaDao.bulkInsertAreas(newAreas)
    }

    suspend fun deleteAllAreasRepo(){
        areaDao.deleteAllAreas()
    }

    companion object{
        @Volatile
        private var instance: AreaRepository? = null

        fun getInstance(areaDao: AreaDao) =
            instance ?: synchronized(this){
                instance ?: AreaRepository(areaDao).also { instance = it }
            }
    }
}