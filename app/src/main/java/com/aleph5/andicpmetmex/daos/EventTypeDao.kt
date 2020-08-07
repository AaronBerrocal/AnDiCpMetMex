package com.aleph5.andicpmetmex.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aleph5.andicpmetmex.entities.EventTypeEntity

@Dao
interface EventTypeDao {

    @Query("SELECT * FROM a09_tipo_evento WHERE activo = 1")
    fun loadAllEventTypes(): LiveData<List<EventTypeEntity>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkInsertEventTypes(newEventTypes: List<EventTypeEntity>)

    @Query("DELETE FROM a09_tipo_evento")
    suspend fun deleteAllEventTypes()

}