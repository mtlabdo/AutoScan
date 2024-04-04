package com.vehicle.immatriculation.vin.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FeedbackDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (String) -> Unit
) {
    if (showDialog) {
        var feedbackText by remember { mutableStateOf("") }
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text("Donnez-nous votre avis") },
            text = {
                Column(
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text("Quelles fonctionnalités aimeriez-vous voir dans la prochaine version ?")
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = feedbackText,
                        onValueChange = { feedbackText = it },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp), // Ajuster la hauteur
                        singleLine = false // Permettre plusieurs lignes
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onSubmit(feedbackText)
                        onDismiss()
                    }
                ) {
                    Text("Envoyer", color = Color.White)
                }
            },
            dismissButton = {
                Button(
                    onClick = { onDismiss() }
                ) {
                    Text("Annuler", color = Color.White)
                }
            }
        )
    }
}
