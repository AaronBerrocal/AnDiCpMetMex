package com.aleph5.andicpmetmex.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "a05_equipo_planta",
    indices = arrayOf(
        Index(
            value = ["id_equipo"],
            unique = true
        )
    )
)
data class EquipmentEntity (

    @PrimaryKey(autoGenerate = true)
    val uid: Long,

    @ColumnInfo(name = "id_equipo")
    val idEquipo: String,

    @ColumnInfo(name = "equipo_planta")
    val equipoPlanta: String,

    val descripcion: String?,

    val activo: Int, //0 or 1

    @ColumnInfo(name = "id_planta")
    val idPlanta: String,

    @ColumnInfo(name = "id_area")
    val idArea: String,

    @ColumnInfo(name = "id_subarea")
    val idSubrea: String,

    val signature: String // idEquipo+" - "+equipo_planta)

)




