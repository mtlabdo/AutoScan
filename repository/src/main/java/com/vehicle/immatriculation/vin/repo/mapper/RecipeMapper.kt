package com.vehicle.immatriculation.vin.repo.mapper


import com.vehicle.immatriculation.vin.model.Detail
import com.vehicle.immatriculation.vin.model.History
import com.vehicle.immatriculation.vin.data.database.entity.HistoryEntity
import com.vehicle.immatriculation.vin.data.remote.model.CarfaxNetwork
import com.vehicle.immatriculation.vin.data.remote.model.ConsultRes
import com.vehicle.immatriculation.vin.data.remote.model.DetailNetwork
import com.vehicle.immatriculation.vin.data.remote.model.OcraNetwork
import com.vehicle.immatriculation.vin.data.remote.model.PgjNetwork
import com.vehicle.immatriculation.vin.data.remote.model.RepuveNetwork
import com.vehicle.immatriculation.vin.data.remote.model.*
import com.vehicle.immatriculation.vin.model.Carfax
import com.vehicle.immatriculation.vin.model.Consult
import com.vehicle.immatriculation.vin.model.Ocra
import com.vehicle.immatriculation.vin.model.Pgj
import com.vehicle.immatriculation.vin.model.*


////////////////////////////////

fun ResultNetwork.toDataModel() = com.vehicle.immatriculation.vin.model.Result(
    repuve = this.repuve?.toDataModel(),
    pgj = this.pgj?.toDataModel(),
    ocra = this.ocra?.toDataModel(),
    carfax = this.carfax?.toDataModel(),
    aviso = this.aviso?.toDataModel(),
)


fun PgjNetwork.toDataModel() = Pgj(
    vin = vin,
    placa = placa,
    idEstatusVhiRobo = idEstatusVhiRobo,
    estatusVhiRobo = estatusVhiRobo,
    fteVhiRobo = fteVhiRobo,
    fechaActualiza = fechaActualiza,
    fechaRobo = fechaRobo,
    fechaAverigua = fechaAverigua,
    fteRecupera = fteRecupera,
    fecActRec = fecActRec
)

fun OcraNetwork.toDataModel() = Ocra(
    conReporteRoboRecuperacion = conReporteRoboRecuperacion,
    reporte = reporte?.toDataModel(),
    vehiculo = vehiculo?.toDataModel(),
    reporteRobo = reporteRobo?.toDataModel(),
    reporteRecuperacion = null // TODO I DON'T NOW THE CONTENT
)

fun CarfaxNetwork.toDataModel() = Carfax(
    statusCode = statusCode,
    message = message,
    _id = _id,
    data = data?.toDataModel()
)

fun CarfaxDataNetwork.toDataModel() = CarfaxData(
    robo = robo,
    message = message
)

fun AvisoNetwork.toDataModel() = Aviso(
    robo = robo
)


fun ReporteNetwork.toDataModel() = Reporte(
    roboORecuperacion = roboORecuperacion,
    estatus = estatus
)

fun VehiculoNetwork.toDataModel() = Vehiculo(
    numeroSerie = numeroSerie,
    idEstatusPlaca = idEstatusPlaca,
    estatusPlaca = estatusPlaca,
    idEstatusVehiculo = idEstatusVehiculo,
    estatusVehiculo = estatusVehiculo,
    idOrigen = idOrigen,
    origen = origen,
    idColor = idColor,
    color = color,
    idMarca = idMarca,
    marca = marca,
    idTipoSubmarca = idTipoSubmarca,
    tipoSubmarca = tipoSubmarca,
    idTipoDeTransporte = idTipoDeTransporte,
    tipoDeTransporte = tipoDeTransporte,
    modelo = modelo,
    placa = placa,
    motor = motor
)

fun ReporteRoboNetwork.toDataModel() = ReporteRobo(
    idEstusReporte = idEstusReporte,
    estusReporte = estusReporte,
    codigoPostal = codigoPostal,
    idEstado = idEstado,
    estado = estado,
    idMunicipio = idMunicipio,
    municipio = municipio,
    idColonia = idColonia,
    colonia = colonia,
    calle = calle,
    idTipoRobo = idTipoRobo,
    tipoRobo = tipoRobo,
    fechaRobo = fechaRobo,
    actaRobo = actaRobo,
    numeroAsaltantes = numeroAsaltantes,
    carretera = carretera
)

fun RepuveNetwork.toDataModel() = Repuve(
    placa = placa,
    clase = clase,
    marca = marca,
    modelo = modelo,
    anioModelo = anioModelo,
    tipo = tipo,
    complemento = complemento,
    idImportacion = idImportacion,
    nrpv = nrpv,
    idInstitucion = idInstitucion,
    nombre = nombre,
    vin = vin,
    numPuertas = numPuertas,
    paisOrigen = paisOrigen,
    linea = linea,
    cilindrada = cilindrada,
    numCilindros = numCilindros,
    numEjes = numEjes,
    plantaEnsamble = plantaEnsamble,
    senas = senas,
    folioRpv = folioRpv,
    fechaRegistro = fechaRegistro,
    horaRegistro = horaRegistro,
    idEntidad = idEntidad,
    entidadEmplaco = entidadEmplaco,
    fechaExpedicion = fechaExpedicion,
    fechaActualiza = fechaActualiza,
    tipoMovimiento = tipoMovimiento,
    movimiento = movimiento
)


fun DetailNetwork.toDataModel(): Detail {
    return Detail(
        id = this.id,
        result = result?.toDataModel(),
        placas = this.placas,
        niv = this.niv,
        detail = detail
    )
}
////////////////////////////////

fun HistoryEntity.toDataModel() = History(
    id = id,
    plateNumber = plateNumber,
)

fun ConsultRes.toDataModel() = Consult(
    id = id,
    status = status,
)

fun List<HistoryEntity>.toListDataModel() = this.map {
    it.toDataModel()
}
