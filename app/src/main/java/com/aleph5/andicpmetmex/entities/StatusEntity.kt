package com.aleph5.andicpmetmex.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "a12_estatus",
    indices = arrayOf(
        Index(
            value = ["id_estatus", "id_modulo"],
            unique = true
        )
    )
)
data class StatusEntity(

    @PrimaryKey(autoGenerate = true)
    val uid: Long,

    @ColumnInfo(name = "id_estatus")
    val idEstatus: String,

    val estatus: String,

    val descripcion: String?,

    val activo: Int, //0 or 1

    @ColumnInfo(name = "id_modulo")
    val idModulo: String,

    val signature: String // idEstatus+" - "+estatus)

)