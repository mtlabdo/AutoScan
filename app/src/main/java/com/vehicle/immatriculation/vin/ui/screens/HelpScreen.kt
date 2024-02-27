package com.vehicle.immatriculation.vin.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vehicle.immatriculation.vin.R
import com.vehicle.immatriculation.vin.navigation.AppState
import com.vehicle.immatriculation.vin.ui.widget.BackUiComposable
import com.vehicle.immatriculation.vin.utils.Const

@Composable
fun HelpScreen(appState: AppState) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 0.dp, end = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BackUiComposable {
                appState.onBackClick()
            }

            Spacer(modifier = Modifier.width(24.dp))

            Text(
                text = "Aide",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Bienvenue dans l'aide de l'application",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        LazyColumn {
            items(helpItems) { helpItem ->
                HelpItemCard(helpItem)
            }
        }
    }
}

@Composable
fun HelpItemCard(helpItem: HelpItem) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp), // Légère augmentation de l'élévation pour plus de profondeur
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = helpItem.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp),
            )
            Text(
                text = helpItem.description,
                style = MaterialTheme.typography.titleMedium,
            )

            helpItem.image?.let {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .clip(shape = CircleShape),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = null,
                        modifier =
                            Modifier
                                .clip(shape = CircleShape)
                                .height(100.dp),
                    )
                }
            }
        }
    }
}

data class HelpItem(
    val title: String,
    val description: String,
    @DrawableRes val image: Int? = null,
)

val helpItems =
    listOf(
        HelpItem(
            title = "Comment utiliser la fonction de recherche ?",
            description = "Pour utiliser la fonction de recherche, entrez le numéro de plaque que vous souhaitez rechercher dans la barre de recherche située en haut de l'écran d'accueil.",
            image = R.drawable.example_search_plate,
        ),
        HelpItem(
            title = "Supprimer l'historique de recherche",
            description = "Pour supprimer un élément de l'historique de recherche, appuyez sur l'icône de corbeille à côté de l'élément que vous souhaitez supprimer.",
        ),
        HelpItem(
            title = "Besoin d'aide supplémentaire ?",
            description = "Si vous avez besoin d'aide supplémentaire ou si vous rencontrez des problèmes avec l'application, n'hésitez pas à nous contacter via l'adresse mail suivante : ${Const.HELP_MAIL}",
        ),
    )
