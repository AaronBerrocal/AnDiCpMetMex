package com.aleph5.andicpmetmex.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aleph5.andicpmetmex.entities.PlantEntity

@Dao
interface PlantDao {

    @Query("SELECT signature FROM a02_plantas WHERE activo = 1")
    fun loadAllPlantSignatures(): LiveData<List<String>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkInsertPlants(newPlants: List<PlantEntity>)

    @Query("DELETE FROM a02_plantas")
    suspend fun deleteAllPlants()

}