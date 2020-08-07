package com.aleph5.andicpmetmex.repositories

import androidx.lifecycle.LiveData
import com.aleph5.andicpmetmex.daos.EquipmentDao
import com.aleph5.andicpmetmex.entities.EquipmentEntity

class EquipmentRepository private constructor(private val equipmentDao: EquipmentDao){

    val equipments: LiveData<List<EquipmentEntity>> = equipmentDao.loadAllEquipments()

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