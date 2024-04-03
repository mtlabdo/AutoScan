package com.vehicle.immatriculation.vin.data.remote.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/*
status:"processing"
callback:"https://webhook.site/5b4315eb-06fd-4e40-a36c-3b5c9c1c48da"
id:"6601a45dcfe96dfd54ddfb28"
 */

@Keep
data class ConsultRes(
    @SerializedName("status") val status: String? = null,
    @SerializedName("callback") val callback: String? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("detail") val detail: ErrorDetail? = null,
)

@Keep
data class ErrorDetail(
    @SerializedName("type") val type: String? = null,
    @SerializedName("msg") val msg: String? = null,
)