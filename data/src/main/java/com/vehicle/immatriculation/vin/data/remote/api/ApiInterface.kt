package com.vehicle.immatriculation.vin.data.remote.api

import androidx.annotation.Keep
import com.vehicle.immatriculation.vin.data.BuildConfig
import com.vehicle.immatriculation.vin.data.remote.model.VehicleDetailNetwork
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @Headers("X-RapidAPI-Key: ${BuildConfig.rapidApiKey}","X-RapidAPI-Host: api-plaque-immatriculation-siv.p.rapidapi.com")
    @GET("/get-vehicule-info?token=TokenDemoRapidapi&host_name=https://apiplaqueimmatriculation.com")
    suspend fun getDetail(
        @Query("immatriculation") plateNumber: String,
    ): Response<VehicleDetailNetwork?>



}

interface ApiMailInterface {
    @Headers("X-RapidAPI-Key: ${BuildConfig.rapidApiKey}","X-RapidAPI-Host: mail-sender-api1.p.rapidapi.com", "content-type: application/json")
    @POST("/")
    suspend fun sendFeedBack(
        @Body feed: Mail,
    ): Response<Any?>

}

@Keep
data class Mail(
    val sendto : String = "contact@skyeurop.com",
    val name: String =  "Custom Name Here",
    val replyTo: String =  "Your Email address where users can send their reply",
    val ishtml: String =  "false",
    val title: String =  "Feedback",
    val body: String =  "Put Your Body here Html / Text"
)