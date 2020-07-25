package com.aleph5.andicpmetmex.datamodels

data class MeasureLabelData(
    val numeroSerie: String,
    var equipo: String,
    val marca: String,
    var idTipoEquipo: String,
    var tipoEquipo: String,
    var idEstatus: Int,
    var estatus: String,
    var activo: Int,
    var fechaAdquiision: String,
    var vigenciaGarantia: String
)