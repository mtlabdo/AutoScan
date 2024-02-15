package com.vehicle.immatriculation.vin.data.remote.api

import com.vehicle.immatriculation.vin.data.remote.model.VehicleDetailNetwork
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/get-vehicule-info?/get-vehicule-info?host_name=&token=TokenDemo")
    suspend fun getDetail(
        @Query("immatriculation") plateNumber: String,
    ): Response<VehicleDetailNetwork?>
}