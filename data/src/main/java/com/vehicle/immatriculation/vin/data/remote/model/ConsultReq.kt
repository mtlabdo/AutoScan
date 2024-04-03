package com.vehicle.immatriculation.vin.data.remote.model

data class ConsultReq(
    val placa_niv: String,
    val callback: String? = ""
)