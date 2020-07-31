package com.aleph5.andicpmetmex.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "eventos",
    indices = arrayOf(
        Index(
            value = ["id_evento"],
            unique = true
        )
    )
)
data class EventEntity(

    @PrimaryKey(autoGenerate = true)
    val uid: Int,

    @ColumnInfo(name = "id_evento")
    val idEvento: String,

    @ColumnInfo(name = "id_planta")
    val idPlanta: String,

    val planta: String,

    @ColumnInfo(name = "id_area")
    val idArea: String,

    val area: String,

    @ColumnInfo(name = "id_subarea")
    val idSubarea: String,

    val subarea: String,

    @ColumnInfo(name = "id_equipo_planta")
    val idEquipoPlanta: String,

    @ColumnInfo(name = "equipo_planta")
    val equipoPlanta: String,

    @ColumnInfo(name = "id_tipo_sistema")
    val idTipoSistema: String,

    @ColumnInfo(name = "tipo_sistema")
    val tipoSistema: String,

    @ColumnInfo(name = "id_tipo_evento")
    val idTipoEvento: Int,

    @ColumnInfo(name = "tipo_evento")
    val tipoEvento: String,

    @ColumnInfo(name = "id_prioridad")
    val idPrioridad: Int,

    val prioridad: String,

    @ColumnInfo(name = "id_estatus")
    val idEstatus: Int,

    val estatus: String,

    @ColumnInfo(name = "nombre_solicitante")
    val nombreSolicitante: String,

    @ColumnInfo(name = "contacto_solicitante")
    val contactoSolicitante: String,

    @ColumnInfo(name = "correo_solicitante")
    val correoSolicitante: String,

    @ColumnInfo(name = "fecha_reporte")
    val fechaReporte: Date,

    @ColumnInfo(name = "id_tipo_falla")
    val idTipoFalla: Int?,

    @ColumnInfo(name = "tipo_falla")
    val tipoFalla: String?,

    @ColumnInfo(name = "id_subtipo_falla")
    val idSubtipoFalla: Int?,

    @ColumnInfo(name = "subtipo_falla")
    val subtipoFalla: String?,

    @ColumnInfo(name = "id_tipo_atencion")
    val idTipoAtencion: Int?,

    @ColumnInfo(name = "tipo_atencion")
    val tipoAtencion: String?,

    val diagnostico: String?,

    @ColumnInfo(name = "fecha_solucion")
    val fechaSolucion: Date?,

    val comentarios: String?,

    @ColumnInfo(name = "nombre_usuario_alta")
    val nombreUsuarioAlta: String?,

    @ColumnInfo(name = "guardia_en_turno")
    val guardiaEnTurno: String?,

    @ColumnInfo(name = "nombre_ejecutor")
    val nombreEjecutor: String?

)