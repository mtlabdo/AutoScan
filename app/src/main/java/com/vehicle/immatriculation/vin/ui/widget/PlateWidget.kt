package com.vehicle.immatriculation.vin.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vehicle.immatriculation.vin.R
import com.vehicle.immatriculation.vin.ui.theme.AutoScanAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlateWidget(
    plateText: String,
    onFocusChange: (Boolean) -> Unit = {},
    onSearchTextChanged: (String) -> Unit = {},
    onSearch: () -> Unit = {}
) {

    var isFocused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp), // Ajustez selon vos besoins
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Définit l'élévation de la Card
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, Color(0xFF27449D), RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .padding(horizontal = 0.dp)
        ) {
            // Partie gauche pour l'icône
            Box(
                modifier = Modifier
                    .background(Color(0xFF27449D))
                    .fillMaxHeight()
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.union_eur),
                    contentDescription = "EU Flag",
                    modifier = Modifier.size(28.dp),
                    tint = Color(0xFFFFDD00)
                )
            }

            // Champ de texte central
            OutlinedTextField(
                value = plateText,
                onValueChange = { newText ->
                    onSearchTextChanged(newText.uppercase()) // Convertir en majuscules
                },
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 24.sp, // Taille plus grande pour un effet titre
                    fontWeight = FontWeight.Bold, // Gras pour l'importance
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                ),
                label = if (!isFocused) { // Afficher le label seulement si le champ n'est pas en focus
                    {
                        Text(
                            "AA000AA",
                            color = Color.LightGray,
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,// Centrer le label
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .fillMaxWidth()
                        )
                    }
                } else null,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Color(0xFF27449D)
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    onSearch()
                    keyboardController?.hide()
                }),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                        onFocusChange(isFocused)
                    }
            )

            // Partie droite vide pour équilibrer la mise en page
            Spacer(
                modifier = Modifier
                    .background(Color(0xFF27449D))
                    .fillMaxHeight()
                    .width(28.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WidgetPreview() {
    AutoScanAppTheme {
        PlateWidget(plateText = "")
    }
}
