package com.aleph5.andicpmetmex.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "a02_plantas",
    indices = arrayOf(
        Index(
            value = ["id_planta"],
            unique = true
        )
    )
)
data class PlantEntity (

    @PrimaryKey(autoGenerate = true)
    val uid: Long,

    @ColumnInfo(name = "id_planta")
    val idPlanta: String,

    val planta: String,

    @ColumnInfo(name = "id_usuario")
    val idUsuario: String?,

    val activo: Int, //0 or 1

    val signature: String // idPlanta+" - "+planta

)