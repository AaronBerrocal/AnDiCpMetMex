package com.aleph5.andicpmetmex.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aleph5.andicpmetmex.entities.PriorityEntity

@Dao
interface PriorityDao {

    @Query("SELECT * FROM a13_prioridades WHERE activo = 1")
    fun loadAllPriorities(): LiveData<List<PriorityEntity>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkInsertPriorities(newPriorities: List<PriorityEntity>)

    @Query("DELETE FROM a13_prioridades")
    suspend fun deleteAllPriorities()
}