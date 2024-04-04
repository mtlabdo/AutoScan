package com.vehicle.immatriculation.vin.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

fun openUrl(
    context: Context,
    url: String,
) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}