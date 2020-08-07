package com.aleph5.andicpmetmex.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aleph5.andicpmetmex.entities.EquipmentEntity

@Dao
interface EquipmentDao {

    @Query("SELECT * FROM a05_equipo_planta WHERE activo = 1")
    fun loadAllEquipments(): LiveData<List<EquipmentEntity>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkInsertEquipment(newEquipment: List<EquipmentEntity>)

    @Query("DELETE FROM a05_equipo_planta")
    suspend fun deleteAllEquipment()

}