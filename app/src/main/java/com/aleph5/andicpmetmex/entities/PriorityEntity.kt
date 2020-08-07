package com.aleph5.andicpmetmex.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "a13_prioridades",
    indices = arrayOf(
        Index(
            value = ["id_prioridad"],
            unique = true
        )
    )
)
data class PriorityEntity(

    @PrimaryKey(autoGenerate = true)
    val uid: Long,

    @ColumnInfo(name = "id_prioridad")
    val idPrioridad: String,

    val prioridad: String,

    val activo: Int, //0 or 1

    val signature: String // idPrioridad+" - "+prioridad)

)