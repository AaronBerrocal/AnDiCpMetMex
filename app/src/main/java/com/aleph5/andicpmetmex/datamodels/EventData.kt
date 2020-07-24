package com.aleph5.andicpmetmex.datamodels

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class EventData (
    val idPlanta: String,
    val planta: String,
    val idArea: String,
    val area: String,
    val idSubarea: String,
    val subarea: String,
    val idEquipoPlanta: String,
    val equipoPlanta: String,
    val idTipoSistema: String,
    val tipoSistema: String,
    val idTipoEvento: String,
    val tipoEvento: String,
    val nombreSolicitante: String,
    val contactoSolicitante: String,
    val correoSolicitante: String
) {

    val idEvento: String
    val fechaReporte: String
    var idPrioridad: Int = 2
    var prioridad: String = "Media"
    var idEstatus: Int = 1
    var estatus: String = "Alta"

    init {
        idEvento = UUID.randomUUID().toString()
        fechaReporte =
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
            }else{
                SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(Date())
            }
    }

    lateinit var idTipoFalla: String
    lateinit var tipoFalla: String
    lateinit var idSubtipoFalla: String
    lateinit var subtipoFalla: String
    lateinit var idTipoAtencion: String
    lateinit var tipoAtencion: String
    lateinit var diagnostico: String
    lateinit var fechaSolucion: String
    lateinit var comentarios: String
    lateinit var nombreUsuarioAlta: String
    lateinit var guardiaEnTurno: String
    lateinit var nombreEjecutor: String

}