package com.vehicle.immatriculation.vin.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vehicle.immatriculation.vin.R
import com.vehicle.immatriculation.vin.navigation.AppState
import com.vehicle.immatriculation.vin.ui.theme.AutoScanAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(appState: AppState) {
    val context = LocalContext.current // To access string resources
    TopAppBar(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(top = 16.dp)
                .background(MaterialTheme.colorScheme.primary),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier =
                    Modifier
                        .fillMaxWidth(),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_icon),
                    contentDescription = context.getString(R.string.home_title),
                    modifier =
                        Modifier
                            .padding(end = 16.dp)
                            .size(60.dp)
                            .clip(RoundedCornerShape(8.dp)),
                )
                Text(
                    context.getString(R.string.home_title),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W400),
                )
            }
        },
        actions = {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = { appState.navigateToSettings() },
                    modifier =
                        Modifier
                            .padding(16.dp)
                            .size(32.dp) // Adjusted size for Settings icon
                            .clip(RoundedCornerShape(8.dp)),
                ) {
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = context.getString(R.string.settings),
                    )
                }
                IconButton(
                    onClick = { appState.navToHelp() },
                    modifier =
                        Modifier
                            .padding(16.dp)
                            .size(32.dp) // Adjusted size for Help icon
                            .clip(RoundedCornerShape(8.dp)),
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.Help,
                        contentDescription = context.getString(R.string.help_icon_content_description),
                    )
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AutoScanAppTheme {
    }
}
