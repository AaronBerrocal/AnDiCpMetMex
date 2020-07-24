package com.aleph5.andicpmetmex.datamodels

class ComputingEquipmentData(
    val idTipoEquipo: String,
    val tipoEquipo: String,
    val serviceTag: String,
    var idProveedor: String,
    var proveedor: String,
    var idModalidad: Int,
    var modalidad: String,
    var idEstatusEquipo: Int,
    var estatusEquipo: String,
    var rentaMensualEquipo: Double,
    var fechaVencimientoGarantiaEquipo: String,
    val anexo: String,
    var idEstatusAnexo: Int,
    var estatusAnexo: String,
    var fechaVencimientoAnexo: String
) {

    lateinit var idPlanta: String
    lateinit var planta: String
    lateinit var idArea: String
    lateinit var area: String
    lateinit var sistemaControl: String
    lateinit var nombreEquipo: String
    lateinit var localizacion: String
    lateinit var encargadoArea: String
}