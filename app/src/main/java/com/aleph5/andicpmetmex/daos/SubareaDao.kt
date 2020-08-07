package com.aleph5.andicpmetmex.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aleph5.andicpmetmex.entities.SubareaEntity

@Dao
interface SubareaDao {

    @Query("SELECT * FROM a04_subareas WHERE activo = 1")
    fun loadAllSubareas(): LiveData<List<SubareaEntity>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkInsertSubareas(newSubareas: List<SubareaEntity>)

    @Query("DELETE FROM a04_subareas")
    suspend fun deleteAllSubareas()

}