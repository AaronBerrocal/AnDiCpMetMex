package com.aleph5.andicpmetmex.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aleph5.andicpmetmex.entities.StatusEntity

@Dao
interface StatusDao {

    @Query("SELECT * FROM a12_estatus WHERE id_modulo = :selectedModuleId AND activo = 1")
    fun searchStatusByModuleId(selectedModuleId: String): LiveData<List<StatusEntity>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkInsertStatus(newStatus: List<StatusEntity>)

    @Query("DELETE FROM a12_estatus")
    suspend fun deleteAllStatus()

}