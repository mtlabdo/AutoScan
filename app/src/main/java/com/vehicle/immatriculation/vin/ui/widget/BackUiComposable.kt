package com.vehicle.immatriculation.vin.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BackUiComposable(onBackClicked: () -> Unit) {
    Card(
        modifier =
            Modifier
                .width(52.dp)
                .height(52.dp), // Ajustez le padding selon vos besoins
        shape = CardDefaults.outlinedShape, // Utilisez RectangleShape pour une CardView rectangulaire
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(8.dp), // Ajustez le padding pour positionner correctement l'icône à l'intérieur de la Card
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            IconButton(
                onClick = { onBackClicked() },
                modifier = Modifier.size(32.dp), // Ajustez la taille de l'icône si nécessaire
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Retour",
                )
            }
        }
    }
}
