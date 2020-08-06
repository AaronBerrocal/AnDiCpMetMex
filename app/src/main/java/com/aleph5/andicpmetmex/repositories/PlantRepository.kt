package com.aleph5.andicpmetmex.repositories

import androidx.lifecycle.LiveData
import com.aleph5.andicpmetmex.daos.PlantDao
import com.aleph5.andicpmetmex.entities.PlantEntity

class PlantRepository private constructor(private val plantDao: PlantDao) {

    val plants: LiveData<List<PlantEntity>> = plantDao.loadAllPlants()

    suspend fun bulkInsertPlantsRepo(newPlants: List<PlantEntity>){
        plantDao.bulkInsertPlants(newPlants)
    }

    suspend fun deleteAllPlantsRepo(){
        plantDao.deleteAllPlants()
    }

    companion object{
        @Volatile
        private var instance: PlantRepository? = null

        fun getInstance(plantDao: PlantDao) =
            instance ?: synchronized(this){
                instance ?: PlantRepository(plantDao).also { instance = it }
            }
    }
}