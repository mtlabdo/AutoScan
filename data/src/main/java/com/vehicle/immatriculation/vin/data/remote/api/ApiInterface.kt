package com.vehicle.immatriculation.vin.data.remote.api

import androidx.annotation.Keep
import com.vehicle.immatriculation.vin.data.BuildConfig
import com.vehicle.immatriculation.vin.data.remote.model.ApiResponse
import com.vehicle.immatriculation.vin.data.remote.model.VehicleDetailNetwork
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryName

interface ApiInterface {

    @Headers("X-RapidAPI-Key: ${BuildConfig.rapidApiKey}","X-RapidAPI-Host: api-plaque-immatriculation-siv.p.rapidapi.com")
    @GET("/get-vehicule-info?token=TokenDemoRapidapi&host_name=https://apiplaqueimmatriculation.com")
    suspend fun getDetail(
        @Query("immatriculation") plateNumber: String,
    ): Response<VehicleDetailNetwork?>

    @Headers("X-Api-Key: 8kFCwDuKmZ35fTqkV8UPVR2xzvZAV6uY","X-Firebase-Appcheck: eyJraWQiOiJYcEhKU0EiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxOjcwODQzNjkyMjA0MTp3ZWI6MTkyNDBlZTc0NDg2N2JmZmM0MTU2OCIsImF1ZCI6WyJwcm9qZWN0c1wvNzA4NDM2OTIyMDQxIiwicHJvamVjdHNcL2ZyLXZlaGljdWxlcyJdLCJwcm92aWRlciI6InJlY2FwdGNoYV92MyIsImlzcyI6Imh0dHBzOlwvXC9maXJlYmFzZWFwcGNoZWNrLmdvb2dsZWFwaXMuY29tXC83MDg0MzY5MjIwNDEiLCJleHAiOjE3MTMyNzAzMzUsImlhdCI6MTcxMzE4MzkzNSwianRpIjoiSXBOemZYQTJ1d0syY1gtMFFiTkJSUmNFbkF4ekdiOFlUTHJmVnBJaDRJRSJ9.RR4jixrERxxqocI-zQDzRYcUevs0b_pkA4NUUd4ks14VsOZg8zJMb9rBoVZqQY-GnyzaVZDAJw5yjN-hug5IjeqqnqVUSzZuGTJ0HDdtkEn5MV5KUz-QgYrO9LYmz_I-sGTldiaSRrhK_sAu0XrLiuB4P2WRmRaVLNepRRatdG5zAiQvjZjfKYc5pgZs2g_Z5Hsef6Jjol_6MVj8lZY_8V_GFN5RjmvVTYFIwlW3R5k1e0hDZTbLjAY1jWttRFLuHJwcF-rsGDk5cE1Weo66fvBJWJnMF9fDnbZT37URS2yBI2FFo4WBpQVCZ91N2E9JSIZuzRXFSgvVgaPRBfZBfSyWsCkhPw3SPd3ySnklLISvunB_HCZ63qWBtUtNFn-oZdDgFBneFKSNGTFFu_95Y25pd4rYGTm5w4wwD8L_5UVN-Q7Gb8X8nwXMxxG-uS6lbDrXS7EOgG7rY2sb_xGWV_JlKDUATkwnRDk0Ih5g0vwj1O7p4D3YegRCcK3pHDgm")
    @GET("https://api.immatriculation-auto.info/vehicles/{plateNumber}")
    suspend fun _getDetail(
        @Path("plateNumber") plateNumber: String,
        ): Response<ApiResponse?>
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