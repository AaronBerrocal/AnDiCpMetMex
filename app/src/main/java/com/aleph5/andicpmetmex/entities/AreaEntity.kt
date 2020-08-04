package com.aleph5.andicpmetmex.entities

import androidx.room.*

@Entity(tableName = "a03_areas",
    indices = arrayOf(
        Index(
            value = ["id_area"],
            unique = true
        ),
        Index(
            value = ["id_area", "id_planta"],
            unique = true
        ),
        Index(
            value = ["id_planta"]
        )
    ),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = PlantEntity::class,
            parentColumns = arrayOf("id_planta"),
            childColumns = arrayOf("id_planta"),
            onDelete = ForeignKey.CASCADE,
            deferred = true
        )
    )
)
data class AreaEntity(

    @PrimaryKey(autoGenerate = true)
    val uid: Long,

    @ColumnInfo(name = "id_area")
    val idArea: String,

    val area: String,

    @ColumnInfo(name = "id_usuario")
    val idUsuario: String?,

    val descripcion: String?,

    val activo: Int, //0 or 1

    @ColumnInfo(name = "id_planta")
    val idPlanta: String,

    val signature: String // idArea+" - "+area

)