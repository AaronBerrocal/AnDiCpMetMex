package com.aleph5.andicpmetmex.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aleph5.andicpmetmex.entities.EventEntity

@Dao
interface EventDao {

    @Query("SELECT * FROM eventos ORDER BY fecha_reporte DESC")
    fun loadAllEvents(): LiveData<List<EventEntity>>

    @Query("SELECT count(*) FROM eventos")
    fun countEvents(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(newEvent: EventEntity)

    @Query("DELETE FROM eventos")
    suspend fun deleteAllEvents()



}