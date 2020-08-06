package com.aleph5.andicpmetmex.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aleph5.andicpmetmex.entities.AreaEntity

@Dao
interface AreaDao {

    @Query("SELECT * FROM a03_areas WHERE activo = 1")
    fun loadAllAreas(): LiveData<List<AreaEntity>>

    @Query("SELECT signature FROM a03_areas WHERE id_planta = :selectedPlantId AND activo = 1 ")
    fun searchAreaSignaturesByPlantId(selectedPlantId: String): LiveData<List<String>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkInsertAreas(newAreas: List<AreaEntity>)

    @Query("DELETE FROM a03_areas")
    suspend fun deleteAllAreas()

}