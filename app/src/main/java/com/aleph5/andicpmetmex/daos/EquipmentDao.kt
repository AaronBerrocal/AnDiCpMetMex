package com.aleph5.andicpmetmex.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aleph5.andicpmetmex.entities.EquipmentEntity

@Dao
interface EquipmentDao {

    @Query("SELECT signature FROM a05_equipo_planta WHERE activo = 1")
    fun loadAllEquipmentSignatures(): LiveData<List<String>>

    @Query("SELECT signature FROM a05_equipo_planta WHERE id_subarea = :selectedSubareaId AND activo = 1")
    fun searchEquipmentSignaturesBySubareaId(selectedSubareaId: String): LiveData<List<String>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkInsertEquipment(newEquipment: List<EquipmentEntity>)

    @Query("DELETE FROM a05_equipo_planta")
    suspend fun deleteAllEquipment()

}