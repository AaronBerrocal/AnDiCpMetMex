package com.aleph5.andicpmetmex.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "a06_tipo_sistema",
    indices = arrayOf(
        Index(
            value = ["id_tipo_sistema", "id_modulo"],
            unique = true
        )
    )
)
data class SystemTypeEntity(

    @PrimaryKey(autoGenerate = true)
    val uid: Long,

    @ColumnInfo(name = "id_tipo_sistema")
    val idTipoSistema: String,

    @ColumnInfo(name = "tipo_sistema")
    val tipoSistema: String,

    val descripcion: String?,

    val activo: Int, //0 or 1

    @ColumnInfo(name = "id_modulo")
    val idModulo: String,

    val signature: String // idTipoSistema+" - "+tipoSistema)

)