package com.aleph5.andicpmetmex.repositories

import androidx.lifecycle.LiveData
import com.aleph5.andicpmetmex.daos.EquipmentDao
import com.aleph5.andicpmetmex.entities.EquipmentEntity

class EquipmentRepository private constructor(private val equipmentDao: EquipmentDao){

    val equipmentSignatures: LiveData<List<String>> = equipmentDao.loadAllEquipmentSignatures()

    fun searchEquipmentSignaturesBySubareaIdRepo(selectedSubareaId: String): LiveData<List<String>>{
        return equipmentDao.searchEquipmentSignaturesBySubareaId(selectedSubareaId)
    }

    suspend fun bulkInsertEquipmentRepo(newEquipment: List<EquipmentEntity>){
        equipmentDao.bulkInsertEquipment(newEquipment)
    }

    suspend fun deleteAllEquipmentRepo(){
        equipmentDao.deleteAllEquipment()
    }

    companion object{
        private var instance: EquipmentRepository? = null

        fun getInstance(equipmentDao: EquipmentDao) =
            instance ?: synchronized(this){
                instance ?: EquipmentRepository(equipmentDao).also { instance = it }
            }
    }
}