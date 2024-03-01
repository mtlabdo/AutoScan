package com.vehicle.immatriculation.vin

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.skydoves.snitcher.Snitcher
import com.skydoves.snitcher.TraceStrategy
import com.skydoves.snitcher.model.SnitcherException
import com.vehicle.immatriculation.vin.exception.ExceptionTraceActivity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AutoScanApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Snitcher.install(
            application = this,
            traceActivity = ExceptionTraceActivity::class,
            traceStrategy = TraceStrategy.CO_WORK,
            exceptionHandler = { exception: SnitcherException ->
                Firebase.crashlytics.log(exception.stackTrace)
            }
        )
    }
}
