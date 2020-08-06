package com.aleph5.andicpmetmex.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aleph5.andicpmetmex.entities.SystemTypeEntity

@Dao
interface SystemTypeDao {

    @Query("SELECT signature FROM a06_tipo_sistema WHERE id_modulo = :selectedModuleId AND activo = 1")
    fun searchSystemTypesByModuleId(selectedModuleId: String): LiveData<List<String>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkInsertSystemTypes(newSystemTypes: List<SystemTypeEntity>)

    @Query("DELETE FROM a06_tipo_sistema")
    suspend fun deleteAllSystemTypes()

}