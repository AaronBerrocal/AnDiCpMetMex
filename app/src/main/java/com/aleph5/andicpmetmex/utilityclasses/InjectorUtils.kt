package com.aleph5.andicpmetmex.utilityclasses

import android.content.Context
import com.aleph5.andicpmetmex.database.AnDiCpRoomDatabase
import com.aleph5.andicpmetmex.repositories.*
import com.aleph5.andicpmetmex.viewmodels.AdministracionEventosViewModelFactory
import kotlinx.coroutines.GlobalScope

object InjectorUtils {

    private fun getEventRepository(context: Context): EventRepository{
        return EventRepository.getInstance(
            AnDiCpRoomDatabase.getInstance(context.applicationContext, GlobalScope).eventDao()
        )
    }

    private fun getPlantRepository(context: Context): PlantRepository{
        return PlantRepository.getInstance(
            AnDiCpRoomDatabase.getInstance(context.applicationContext, GlobalScope).plantDao()
        )
    }

    private fun getAreaRepository(context: Context): AreaRepository{
        return AreaRepository.getInstance(
            AnDiCpRoomDatabase.getInstance(context.applicationContext, GlobalScope).areaDao()
        )
    }

    private fun getSubareaRepository(context: Context): SubareaRepository{
        return SubareaRepository.getInstance(
            AnDiCpRoomDatabase.getInstance(context.applicationContext, GlobalScope).subareaDao()
        )
    }

    private fun getEquipmentRepository(context: Context): EquipmentRepository{
        return EquipmentRepository.getInstance(
            AnDiCpRoomDatabase.getInstance(context.applicationContext, GlobalScope).equipmentDao()
        )
    }

    private fun getSystemTypeRepository(context: Context): SystemTypeRepository{
        return SystemTypeRepository.getInstance(
            AnDiCpRoomDatabase.getInstance(context.applicationContext, GlobalScope).systemTypeDao()
        )
    }

    private fun getEventTypeRepository(context: Context): EventTypeRepository{
        return EventTypeRepository.getInstance(
            AnDiCpRoomDatabase.getInstance(context.applicationContext, GlobalScope).eventTypeDao()
        )
    }

    private fun getPriorityRepository(context: Context): PriorityRepository{
        return PriorityRepository.getInstance(
            AnDiCpRoomDatabase.getInstance(context.applicationContext, GlobalScope).priorityDao()
        )
    }

    private fun getStatusRepository(context: Context): StatusRepository{
        return StatusRepository.getInstance(
            AnDiCpRoomDatabase.getInstance(context.applicationContext, GlobalScope).statusDao()
        )
    }

    fun provideAdministracionEventosViewModelFactory(
        context: Context
    ): AdministracionEventosViewModelFactory{
        return AdministracionEventosViewModelFactory(
            getEventRepository(context),
            getPlantRepository(context),
            getAreaRepository(context),
            getSubareaRepository(context),
            getEquipmentRepository(context),
            getSystemTypeRepository(context),
            getEventTypeRepository(context),
            getPriorityRepository(context),
            getStatusRepository(context)
        )
    }
}