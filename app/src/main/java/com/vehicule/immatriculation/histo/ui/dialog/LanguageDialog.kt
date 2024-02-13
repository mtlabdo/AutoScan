package com.vehicule.immatriculation.histo.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.LocaleListCompat
import com.vehicule.immatriculation.histo.R
import com.vehicule.immatriculation.histo.ui.widget.langue.LanguageOption
import com.vehicule.immatriculation.histo.ui.widget.langue.LanguagePicker
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
        verticalArrangement = Arrangement.Center
    ) {
        LanguagePicker(
            context.getString(R.string.select_language), // Utiliser la chaîne de ressources
            options,
            onDismissRequest = onDismiss,
            onApplyEvent = {
                onApply(it)
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(Locale(it)))
            },
            currentLanguage = context.getString(R.string.language_code).lowercase()
        )
    }
}
