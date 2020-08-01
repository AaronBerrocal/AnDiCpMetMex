package com.aleph5.andicpmetmex.utilityclasses

import android.content.Context
import com.aleph5.andicpmetmex.database.AnDiCpRoomDatabase
import com.aleph5.andicpmetmex.repositories.EventRepository
import com.aleph5.andicpmetmex.viewmodels.AdministracionEventosViewModelFactory
import kotlinx.coroutines.GlobalScope

object InjectorUtils {

    private fun getEventRepository(context: Context): EventRepository{
        return EventRepository.getInstance(
            AnDiCpRoomDatabase.getInstance(context.applicationContext, GlobalScope).EventDao()
        )
    }

    fun provideAdministracionEventosViewModelFactory(
        context: Context
    ): AdministracionEventosViewModelFactory{
        return AdministracionEventosViewModelFactory(getEventRepository(context))
    }
}