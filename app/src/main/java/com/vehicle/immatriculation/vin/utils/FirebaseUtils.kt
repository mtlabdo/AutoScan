package com.vehicle.immatriculation.vin.utils

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.analytics.ktx.analytics

object FirebaseUtils {
    private val firebaseAnalytics = Firebase.analytics

    fun logDemandVehicleHistory(plateNumber: String) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.CURRENCY, plateNumber ?: "")
        bundle.putDouble(FirebaseAnalytics.Param.VALUE, 0.0)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART, bundle)
    }
}