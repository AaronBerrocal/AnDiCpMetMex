package com.aleph5.andicpmetmex.repositories

import com.aleph5.andicpmetmex.daos.PlantDao
import com.aleph5.andicpmetmex.entities.PlantEntity

class PlantRepository private constructor(private val plantDao: PlantDao) {

    val plantSignatures: List<String> = plantDao.loadAllPlantSignatures()

    fun searchBySignatureRepo(currentSignature: String) : PlantEntity {
        return plantDao.searchBySignature(currentSignature)
    }

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