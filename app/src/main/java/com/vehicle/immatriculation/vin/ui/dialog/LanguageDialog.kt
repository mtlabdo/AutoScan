package com.vehicle.immatriculation.vin.ui.dialog

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.LocaleListCompat
import com.vehicle.immatriculation.vin.R
import com.vehicle.immatriculation.vin.ui.widget.langue.LanguageOption
import com.vehicle.immatriculation.vin.ui.widget.langue.LanguagePicker
import java.util.Locale

@Composable
fun LanguageDialog(
    options: List<LanguageOption> = emptyList(),
    onDismiss: () -> Unit,
    onApply: (lang: String) -> Unit,
) {
    val context = LocalContext.current // Pour accéder aux ressources

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LanguagePicker(
            context.getString(R.string.select_language), // Utiliser la chaîne de ressources
            options,
            onDismissRequest = onDismiss,
            onApplyEvent = {
                onApply(it)
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(Locale(it)))
            },
            currentLanguage = context.getString(R.string.language_code).lowercase(),
        )
    }
}
