package com.aleph5.andicpmetmex.datamodels

data class ProjectData (
    val idProyecto: String,
    val proyecto: String,
    val responsable: String,
    val idPlanta: String,
    val planta: String,
    val idArea: String,
    val area: String,
    val fechaInicio: String,
    val fechaFin: String,
    val duracion: String,
    val idPrioridad: Int,
    val prioridad: String,
    val idEstatus: Int,
    val estatus: String,
    val avanceMesNesimo: String,
    val avancePropuesto: String,
    val avanceActual: String,
    val fechaEstatus: String,
    val comentarios: String
)