package com.aleph5.andicpmetmex.daos.cataloguedaos

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PlantDao {

    @Query("SELECT signature FROM a02_plantas")
    fun loadPlantSignatures(): List<String>

}