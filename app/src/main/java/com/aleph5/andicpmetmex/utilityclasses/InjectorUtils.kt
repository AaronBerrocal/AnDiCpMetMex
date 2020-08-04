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

    fun provideAdministracionEventosViewModelFactory(
        context: Context
    ): AdministracionEventosViewModelFactory{
        return AdministracionEventosViewModelFactory(
            getEventRepository(context),
            getPlantRepository(context),
            getAreaRepository(context),
            getSubareaRepository(context),
            getEquipmentRepository(context)
        )
    }
}