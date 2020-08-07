package com.aleph5.andicpmetmex.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aleph5.andicpmetmex.entities.AreaEntity

@Dao
interface AreaDao {

    @Query("SELECT * FROM a03_areas WHERE activo = 1")
    fun loadAllAreas(): LiveData<List<AreaEntity>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkInsertAreas(newAreas: List<AreaEntity>)

    @Query("DELETE FROM a03_areas")
    suspend fun deleteAllAreas()

}