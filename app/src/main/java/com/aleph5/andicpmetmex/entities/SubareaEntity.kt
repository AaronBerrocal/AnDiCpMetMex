package com.aleph5.andicpmetmex.entities

import androidx.room.*

@Entity(tableName = "a04_subareas",
    indices = arrayOf(
        Index(
            value = ["id_subarea"],
            unique = true
        ),
        Index(
            value = ["id_subarea", "id_area"],
            unique = true
        ),
        Index(
            value = ["id_area"]
        )
    ),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = AreaEntity::class,
            parentColumns = arrayOf("id_area"),
            childColumns = arrayOf("id_area"),
            onDelete = ForeignKey.CASCADE,
            deferred = true
        )
    )
)
data class SubareaEntity (

    @PrimaryKey(autoGenerate = true)
    val uid: Long,

    @ColumnInfo(name = "id_subarea")
    val idSubarea: String,

    val subarea: String,

    @ColumnInfo(name = "id_usuario")
    val idUsuario: String?,

    val descripcion: String?,

    val activo: Int, //0 or 1

    @ColumnInfo(name = "id_planta")
    val idPlanta: String,

    @ColumnInfo(name = "id_area")
    val idArea: String,

    val signature: String // idSubarea+" - "+subarea)

)