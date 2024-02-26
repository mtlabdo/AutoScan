package com.vehicle.immatriculation.vin.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vehicle.immatriculation.vin.R
import com.vehicle.immatriculation.vin.ui.theme.AutoScanAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallPlateWidget(
    plateText: String,
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(45.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(4.dp))
                .clip(RoundedCornerShape(4.dp))
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
                    modifier = Modifier.size(12.dp),
                    tint = Color(0xFFFFDD00)
                )
            }

            // Texte central affichant la plaque
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = plateText,
                    style = TextStyle(
                        fontSize = 14.sp, // Taille plus grande pour un effet titre
                        fontWeight = FontWeight.Bold, // Gras pour l'importance
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SmallPlateWidgetPreview() {
    AutoScanAppTheme {
        SmallPlateWidget(plateText = "AA000AA")
    }
}
