package com.vehicle.immatriculation.vin.data.remote.api

import com.vehicle.immatriculation.vin.data.BuildConfig
import com.vehicle.immatriculation.vin.data.remote.model.ConsultReq
import com.vehicle.immatriculation.vin.data.remote.model.ConsultRes
import com.vehicle.immatriculation.vin.data.remote.model.DetailNetwork
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @Headers(
        "X-RapidAPI-Key: ${BuildConfig.rapidApiKey}",
        "X-RapidAPI-Host: informacion-vehiculos-de-mexico.p.rapidapi.com"
    )
    @POST("/consulta")
    suspend fun fetchConsult (
        @Body consult: ConsultReq,
    ): Response<ConsultRes?>

    @Headers(
        "X-RapidAPI-Key: ${BuildConfig.rapidApiKey}",
        "X-RapidAPI-Host: informacion-vehiculos-de-mexico.p.rapidapi.com"
    )
    @GET("/consulta")
    suspend fun fetchDetail (
        @Query("uuid") uuid: String,
    ): Response<DetailNetwork?>
}