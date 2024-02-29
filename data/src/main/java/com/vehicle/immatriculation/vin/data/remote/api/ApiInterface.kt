package com.vehicle.immatriculation.vin.data.remote.api

import com.vehicle.immatriculation.vin.data.BuildConfig
import com.vehicle.immatriculation.vin.data.remote.model.VehicleDetailNetwork
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers("X-RapidAPI-Key: ${BuildConfig.rapidApiKey}","X-RapidAPI-Host: api-plaque-immatriculation-siv.p.rapidapi.com")
    @GET("/get-vehicule-info?token=TokenDemoRapidapi&host_name=https://apiplaqueimmatriculation.com")
    suspend fun getDetail(
        @Query("immatriculation") plateNumber: String,
    ): Response<VehicleDetailNetwork?>
}