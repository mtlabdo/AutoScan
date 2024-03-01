package com.vehicle.immatriculation.vin.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vehicle.immatriculation.vin.navigation.AppState
import com.vehicle.immatriculation.vin.ui.theme.AutoScanAppTheme
import com.vehicle.immatriculation.vin.ui.widget.BackUiComposable

@Composable
fun RepportScreen(appState: AppState) {
    AvailabilityScreen() { appState.onBackClick() }
}

@Composable
fun AvailabilityScreen(onGoBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 0.dp, end = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BackUiComposable() { onGoBack() }

            Spacer(modifier = Modifier.width(24.dp))

            Text(
                text = "Historique du véhicule",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        TemporarilyUnavailableScreenContent()
    }
}

@Composable
private fun TemporarilyUnavailableScreenContent() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    ConfirmAnimation()

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp), // Bords arrondis pour la Card
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp), // Légère augmentation de l'élévation pour plus de profondeur
    ) {

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(scrollState)
                .padding(16.dp)
                .testTag("temporarily_unavailable_screen"),
        ) {
            Text(
                text = "Votre demande a été enregistrée avec succès. Vous recevrez une notification dès que le rapport du véhicule sera chargé.",
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Nous recevons actuellement un volume élevé de demandes, ce qui peut entraîner des délais dans le traitement des rapports.",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Nous vous remercions pour votre patience et votre compréhension.",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
            )
        }
    }
}


@Composable
fun ConfirmAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("animation_confirm.json"))

    Box(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(
                composition = composition,
                speed = 1f,
                modifier = Modifier.size(240.dp), // Ajustez la taille selon vos besoins
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TemporarilyUnavailableScreenPreview() {
    AutoScanAppTheme {
        AvailabilityScreen() {}
    }
}
