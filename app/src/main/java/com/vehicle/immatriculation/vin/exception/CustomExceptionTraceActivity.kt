
package com.vehicle.immatriculation.vin.exception

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.skydoves.snitcher.Snitcher
import com.skydoves.snitcher.model.SnitcherException
import com.skydoves.snitcher.ui.AppRestoreScreen
import com.skydoves.snitcher.ui.ExceptionTraceScreen
import com.skydoves.snitcher.ui.theme.SnitcherTheme
import com.vehicle.immatriculation.vin.BuildConfig

open class ExceptionTraceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val exception: SnitcherException? by Snitcher.exception.collectAsState()
            val launcher: String by Snitcher.launcher.collectAsState()

            SnitcherTheme {
                if (exception != null) {
                    if (BuildConfig.DEBUG) {
                        ExceptionTraceScreen(
                            launcher = launcher,
                            snitcherException = exception!!,
                        )
                    } else {
                        AppRestoreScreen(launcher = launcher)
                    }
                }
            }
        }
    }
}
