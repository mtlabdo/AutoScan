package com.vehicle.immatriculation.vin.data.remote.model

import com.google.gson.annotations.SerializedName

data class DetailNetwork(
    @SerializedName("id") val id: String? = null,
    @SerializedName("result") val result: ResultNetwork? = null,
    @SerializedName("placas") val placas: String? = null,
    @SerializedName("niv") val niv: String? = null,
    @SerializedName("detail") val detail: String? = null,
)

data class ResultNetwork(
    @SerializedName("repuve") val repuve: RepuveNetwork? = null,
    @SerializedName("pgj") val pgj: PgjNetwork? = null,
    @SerializedName("ocra") val ocra: OcraNetwork? = null,
    @SerializedName("carfax") val carfax: CarfaxNetwork? = null,
    @SerializedName("aviso") val aviso: AvisoNetwork? = null,
)

data class RepuveNetwork(
    @SerializedName("PLACA") var placa: String? = null,
    @SerializedName("CLASE") val clase: String? = null,
    @SerializedName("MARCA") val marca: String? = null,
    @SerializedName("MODELO") val modelo: String? = null,
    @SerializedName("ANIO_MODELO") val anioModelo: String? = null,
    @SerializedName("TIPO") val tipo: String? = null,
    @SerializedName("COMPLEMENTO") val complemento: String? = null,
    @SerializedName("ID_IMPORTACION") val idImportacion: Int? = null,
    @SerializedName("NRPV") val nrpv: String? = null,
    @SerializedName("ID_INSTITUCION") val idInstitucion: Int? = null,
    @SerializedName("NOMBRE") val nombre: String? = null,
    @SerializedName("VIN") val vin: String? = null,
    @SerializedName("NUM_PUERTAS") val numPuertas: String? = null,
    @SerializedName("PAIS_ORIGEN") val paisOrigen: String? = null,
    @SerializedName("LINEA") val linea: String? = null,
    @SerializedName("CILINDRADA") val cilindrada: String? = null,
    @SerializedName("NUM_CILINDROS") val numCilindros: String? = null,
    @SerializedName("NUM_EJES") val numEjes: String? = null,
    @SerializedName("PLANTA_ENSAMBLE") val plantaEnsamble: String? = null,
    @SerializedName("SENAS") val senas: Any? = null,
    @SerializedName("FOLIO_RPV") val folioRpv: Int? = null,
    @SerializedName("FECHA_REGISTRO") val fechaRegistro: String? = null,
    @SerializedName("HORA_REGISTRO") val horaRegistro: String? = null,
    @SerializedName("ID_ENTIDAD") val idEntidad: Int? = null,
    @SerializedName("ENTIDAD_EMPLACO") val entidadEmplaco: String? = null,
    @SerializedName("FECHA_EXPEDICION") val fechaExpedicion: String? = null,
    @SerializedName("FECHA_ACTUALIZA") val fechaActualiza: String? = null,
    @SerializedName("TIPO_MOVIMIENTO") val tipoMovimiento: Int? = null,
    @SerializedName("MOVIMIENTO") val movimiento: String? = null
)

data class PgjNetwork(
    @SerializedName("VIN") val vin: String? = null,
    @SerializedName("PLACA") val placa: String? = null,
    @SerializedName("ID_ESTATUS_VHI_ROBO") val idEstatusVhiRobo: Int? = null,
    @SerializedName("ESTATUS_VHI_ROBO") val estatusVhiRobo: String? = null,
    @SerializedName("FTE_VHI_ROBO") val fteVhiRobo: String? = null,
    @SerializedName("FECHA_ACTUALIZA") val fechaActualiza: String? = null,
    @SerializedName("FECHA_ROBO") val fechaRobo: String? = null,
    @SerializedName("FECHA_AVERIGUA") val fechaAverigua: String? = null,
    @SerializedName("FTE_RECUPERA") val fteRecupera: Any? = null,
    @SerializedName("FEC_ACT_REC") val fecActRec: Any?
)

data class OcraNetwork(
    @SerializedName("conReporteRoboRecuperacion") val conReporteRoboRecuperacion: String? = null,
    @SerializedName("reporte") val reporte: ReporteNetwork? = null,
    @SerializedName("vehiculo") val vehiculo: VehiculoNetwork? = null,
    @SerializedName("reporteRobo") val reporteRobo: ReporteRoboNetwork? = null,
    @SerializedName("reporteRecuperacion") val reporteRecuperacion: Any? = null,
)

data class ReporteNetwork(
    @SerializedName("roboORecuperacion") val roboORecuperacion: Int? = null,
    @SerializedName("estatus") val estatus: String? = null,
)

data class VehiculoNetwork(
    @SerializedName("numeroSerie") val numeroSerie: String? = null,
    @SerializedName("idEstatusPlaca") val idEstatusPlaca: Int? = null,
    @SerializedName("estatusPlaca") val estatusPlaca: String? = null,
    @SerializedName("idEstatusVehiculo") val idEstatusVehiculo: Int? = null,
    @SerializedName("estatusVehiculo") val estatusVehiculo: String? = null,
    @SerializedName("idOrigen") val idOrigen: Int? = null,
    @SerializedName("origen") val origen: String? = null,
    @SerializedName("idColor") val idColor: Int? = null,
    @SerializedName("color") val color: String? = null,
    @SerializedName("idMarca") val idMarca: Int? = null,
    @SerializedName("marca") val marca: String? = null,
    @SerializedName("idTipoSubmarca") val idTipoSubmarca: Int? = null,
    @SerializedName("tipoSubmarca") val tipoSubmarca: String? = null,
    @SerializedName("idTipoDeTransporte") val idTipoDeTransporte: Int? = null,
    @SerializedName("tipoDeTransporte") val tipoDeTransporte: String? = null,
    @SerializedName("modelo") val modelo: Int? = null,
    @SerializedName("placa") val placa: String? = null,
    @SerializedName("motor") val motor: String? = null,
)

data class ReporteRoboNetwork(
    @SerializedName("idEstusReporte") val idEstusReporte: Int? = null,
    @SerializedName("estusReporte") val estusReporte: String? = null,
    @SerializedName("codigoPostal") val codigoPostal: String? = null,
    @SerializedName("idEstado") val idEstado: Int? = null,
    @SerializedName("estado") val estado: String? = null,
    @SerializedName("idMunicipio") val idMunicipio: Long? = null,
    @SerializedName("municipio") val municipio: String? = null,
    @SerializedName("idColonia") val idColonia: Any? = null,
    @SerializedName("colonia") val colonia: Any? = null,
    @SerializedName("calle") val calle: Any? = null,
    @SerializedName("idTipoRobo") val idTipoRobo: Int? = null,
    @SerializedName("tipoRobo") val tipoRobo: String? = null,
    @SerializedName("fechaRobo") val fechaRobo: String? = null,
    @SerializedName("actaRobo") val actaRobo: Any? = null,
    @SerializedName("numeroAsaltantes") val numeroAsaltantes: Any? = null,
    @SerializedName("carretera") val carretera: Any? = null,
)

data class CarfaxNetwork(
    @SerializedName("statusCode") val statusCode: Int? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("_id") val _id: String? = null,
    @SerializedName("data") val data: CarfaxDataNetwork? = null,
)

data class CarfaxDataNetwork(
    @SerializedName("robo") val robo: Boolean? = null,
    @SerializedName("message") val message: String? = null,
)

data class AvisoNetwork(
    @SerializedName("robo") val robo: Boolean? = null,
)
