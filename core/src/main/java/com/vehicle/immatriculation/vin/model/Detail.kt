package com.vehicle.immatriculation.vin.model

data class Detail(
    var id: String?,
    var result: Result?,
    var placas: String?,
    var niv: String?,
    var detail: String?
)

data class Result(
    var repuve: Repuve?,
    var pgj: Pgj?,
    var ocra: Ocra?,
    var carfax: Carfax?,
    var aviso: Aviso?
)

data class Repuve(
    var placa: String?,
    var clase: String?,
    var marca: String?,
    var modelo: String?,
    var anioModelo: String?,
    var tipo: String?,
    var complemento: String?,
    var idImportacion: Int?,
    var nrpv: String?,
    var idInstitucion: Int?,
    var nombre: String?,
    var vin: String?,
    var numPuertas: String?,
    var paisOrigen: String?,
    var linea: String?,
    var cilindrada: String?,
    var numCilindros: String?,
    var numEjes: String?,
    var plantaEnsamble: String?,
    var senas: Any?,
    var folioRpv: Int?,
    var fechaRegistro: String?,
    var horaRegistro: String?,
    var idEntidad: Int?,
    var entidadEmplaco: String?,
    var fechaExpedicion: String?,
    var fechaActualiza: String?,
    var tipoMovimiento: Int?,
    var movimiento: String?
)

data class Pgj(
    var vin: String?,
    var placa: String?,
    var idEstatusVhiRobo: Int?,
    var estatusVhiRobo: String?,
    var fteVhiRobo: String?,
    var fechaActualiza: String?,
    var fechaRobo: String?,
    var fechaAverigua: String?,
    var fteRecupera: Any?,
    var fecActRec: Any?
)

data class Ocra(
    var conReporteRoboRecuperacion: String?,
    var reporte: Reporte?,
    var vehiculo: Vehiculo?,
    var reporteRobo: ReporteRobo?,
    var reporteRecuperacion: Any?
)

data class Reporte(
    var roboORecuperacion: Int?,
    var estatus: String?
)

data class Vehiculo(
    var numeroSerie: String?,
    var idEstatusPlaca: Int?,
    var estatusPlaca: String?,
    var idEstatusVehiculo: Int?,
    var estatusVehiculo: String?,
    var idOrigen: Int?,
    var origen: String?,
    var idColor: Int?,
    var color: String?,
    var idMarca: Int?,
    var marca: String?,
    var idTipoSubmarca: Int?,
    var tipoSubmarca: String?,
    var idTipoDeTransporte: Int?,
    var tipoDeTransporte: String?,
    var modelo: Int?,
    var placa: String?,
    var motor: String?
)

data class ReporteRobo(
    var idEstusReporte: Int?,
    var estusReporte: String?,
    var codigoPostal: String?,
    var idEstado: Int?,
    var estado: String?,
    var idMunicipio: Long?,
    var municipio: String?,
    var idColonia: Any?,
    var colonia: Any?,
    var calle: Any?,
    var idTipoRobo: Int?,
    var tipoRobo: String?,
    var fechaRobo: String?,
    var actaRobo: Any?,
    var numeroAsaltantes: Any?,
    var carretera: Any?
)

data class Carfax(
    var statusCode: Int?,
    var message: String?,
    var _id: String?,
    var data: CarfaxData?
)

data class CarfaxData(
    var robo: Boolean?,
    var message: String?
)

data class Aviso(
    var robo: Boolean?
)
