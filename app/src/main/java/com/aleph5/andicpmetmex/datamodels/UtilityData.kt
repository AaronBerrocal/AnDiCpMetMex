package com.aleph5.andicpmetmex.datamodels

data class UtilityData (
    val idProveedor : String,
    val proveedor: String,
    val utileria: String,
    var ipAcceso: String,
    var manualUsuario: String,
    var instruccionesAcceso: String,
    var ligaAccesoMovil: String,
    var ligaAccesoWeb: String,
    var activo: Int
)