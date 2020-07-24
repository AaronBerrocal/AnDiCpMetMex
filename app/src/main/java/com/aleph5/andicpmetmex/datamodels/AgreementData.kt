package com.aleph5.andicpmetmex.datamodels

class AgreementData(
    val idProveedor: String,
    val proveedor: String,
    var numeroContrato: String,
    var numeroSoporte: String,
    var vigencia: Int,
    var fechaInicio: String,
    var fechaFin: String,
    var ligaDocumentoConvenio: String,
    var ligaAccesoWeb: String,
    var idEstatus: Int,
    var estatus: String,
    var comentarios: String
)