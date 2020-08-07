package com.aleph5.andicpmetmex.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "a09_tipo_evento",
    indices = arrayOf(
        Index(
            value = ["id_tipo_evento"],
            unique = true
        )
    )
)
data class EventTypeEntity(

    @PrimaryKey(autoGenerate = true)
    val uid: Long,

    @ColumnInfo(name = "id_tipo_evento")
    val idTipoEvento: String,

    @ColumnInfo(name = "tipo_evento")
    val tipoEvento: String,

    val activo: Int, //0 or 1

    val signature: String // id_tipo_evento+" - "+tipo_evento)

)