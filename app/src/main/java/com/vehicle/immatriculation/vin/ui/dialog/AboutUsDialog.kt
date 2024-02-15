package com.vehicle.immatriculation.vin.ui.dialog

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.vehicle.immatriculation.vin.BuildConfig
import com.vehicle.immatriculation.vin.R
import com.vehicle.immatriculation.vin.utils.Const


@Composable
fun AboutUsDialog(
    context: Context,
    onDismiss: () -> Unit,
    appVersion: String = BuildConfig.VERSION_NAME, // Version de l'application
    contactEmail: String = Const.HELP_MAIL // Email de contact
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.5f))
                .fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(16.dp)
                    .width(400.dp)
                    .align(Alignment.Center) // Centre le Card dans le Box
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    // Contenu défilable
                    Text(
                        text = "About Us",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 16.dp),
                        fontWeight = FontWeight.W400
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = context.getString(R.string.welcome_message, context.getString(R.string.app_name)),
                            modifier = Modifier.padding(bottom = 16.dp),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            text = "Version: $appVersion",
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = AnnotatedString("Contact us: $contactEmail"),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.align(Alignment.CenterHorizontally).width(100.dp)
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }
}
