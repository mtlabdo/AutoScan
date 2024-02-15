package com.vehicle.immatriculation.vin.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.app.UiModeManager
import android.content.Context
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vehicle.immatriculation.vin.R
import com.vehicle.immatriculation.vin.navigation.AppState
import com.vehicle.immatriculation.vin.ui.dialog.AboutUsDialog
import com.vehicle.immatriculation.vin.ui.dialog.LanguageDialog
import com.vehicle.immatriculation.vin.ui.theme.ThemePreference
import com.vehicle.immatriculation.vin.ui.theme.loadThemePreference
import com.vehicle.immatriculation.vin.ui.theme.saveThemePreference
import com.vehicle.immatriculation.vin.ui.widget.BackUiComposable
import com.vehicle.immatriculation.vin.utils.openUrl
import com.vehicle.immatriculation.vin.view.state.CardUiState
import com.vehicle.immatriculation.vin.view.state.SettingsState
import com.vehicle.immatriculation.vin.view.viewmodel.SettingViewModel
import com.vehicle.immatriculation.vin.utils.Const


@Composable
fun SettingsScreen(
    viewModel: SettingViewModel, appState: AppState
) {
    val state by viewModel.state.collectAsState()
    SettingContent(state, appState)
}

@Composable
fun SettingContent(
    uiState: SettingsState,
    appState: AppState,
) {
    var isPickingLanguage by remember {
        mutableStateOf(false)
    }

    var isAboutUs by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp)

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 0.dp, end = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackUiComposable {
                appState.onBackClick()
            }

            Spacer(modifier = Modifier.width(24.dp))

            Text(
                text = context.getString(com.vehicle.immatriculation.vin.R.string.settings),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth()

        ) {
            items(uiState.settingCards) {
                SettingCard(card = it) {
                    when (it.id) {
                        1 -> {
                            isPickingLanguage = true
                        }

                        3 -> {
                            switchTheme(context)
                        }

                        4 -> {
                            isAboutUs = true
                        }

                        5 -> {
                            openUrl(context, Const.PRIVACY_URL)
                        }

                        6 -> {
                            openUrl(context, Const.TERM_SERVICE_URL)
                        }

                        7 -> {
                            openUrl(context, Const.TERM_USE_URL)
                        }
                    }
                }
            }
        }

        if (isPickingLanguage) {
            LanguageDialog(options = uiState.languages,
                onDismiss = { isPickingLanguage = !isPickingLanguage },
                onApply = {
                    isPickingLanguage = !isPickingLanguage
                    (context as? Activity)?.recreate()
                })
        }

        if (isAboutUs) {
            AboutUsDialog(
                context = context,
                onDismiss = { isAboutUs = !isAboutUs },
            )
        }
    }
}


@Composable
fun DefaultBox(iconSize: Int) {
    Box(
        contentAlignment = Alignment.CenterEnd, modifier = Modifier.clip(RoundedCornerShape(16.dp))
    ) {
        Icon(
            imageVector = Icons.Sharp.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier
                //.background(CardBackgroundColor)
                .padding(8.dp)
                .size(iconSize.dp)
        )
    }
}

fun switchTheme(context: Context) {
    val themePreference = loadThemePreference(context)
    val newThemePreference = when (themePreference) {
        ThemePreference.LIGHT -> ThemePreference.DARK
        ThemePreference.DARK -> ThemePreference.LIGHT
        ThemePreference.SYSTEM -> if (isSystemInDarMode(context)) ThemePreference.LIGHT else ThemePreference.DARK
    }

    saveThemePreference(context, newThemePreference)
    (context as? Activity)?.recreate()
}

fun isSystemInDarMode(context: Context): Boolean {
    val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    return uiModeManager.nightMode == UiModeManager.MODE_NIGHT_YES
}

@Composable
fun Header(title: String, subtitle: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title, fontSize = 18.sp, fontWeight = FontWeight.SemiBold
        )
        Text(
            text = subtitle, fontSize = 14.sp, fontWeight = FontWeight.Normal
        )
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun SettingCard(
    card: CardUiState, clickEvent: () -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                clickEvent()
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(shape = CircleShape),
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    painter = painterResource(id = card.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(40.dp)
                )
            }

            Box(
                contentAlignment = Alignment.CenterStart, modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = context.getString(card.settingName),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

            }

            if (card.id == 1) {
                Text(
                    text = LocalContext.current.getString(R.string.language_name),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 10.dp),
                    color = Color.Gray
                )
            }
            card.defaultBox = DefaultBox(iconSize = 25)
        }
    }
}