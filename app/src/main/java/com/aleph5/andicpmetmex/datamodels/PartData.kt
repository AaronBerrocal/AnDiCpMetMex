package com.aleph5.andicpmetmex.datamodels

class PartData(
    val numero_serie: String,
    var refaccion: String,
    val idTipoRefaccion: String,
    var idInventario: String,
    var inventario: String,
    var idEstatus: Int,
    var estatus: String,
    var gaveta: Int,
    var caja: Int,
    val fechaRegistro: String
){
    lateinit var idEvento: String
    lateinit var responsable: String
    lateinit var sistemaDeltaV: String
    lateinit var remoteTerminalUnit: String
    lateinit var numeroRequerimiento: String
    lateinit var numeroPedido: String
    lateinit var fechaCompromisoReposicion: String
    lateinit var fechaReposicion: String
    lateinit var nuevoNumeroSerie: String
    lateinit var comentarios: String
}