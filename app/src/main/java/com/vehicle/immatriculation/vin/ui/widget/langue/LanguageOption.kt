package com.vehicle.immatriculation.vin.ui.widget.langue

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

data class LanguageOption(
    val icon: Int,
    val name: String,
    val contentDescription: String? = null,
    val code: String,
)

@Stable
interface LanguagePickerColors {
    val contentColor: Color
    val onContentColor: Color
    val selectedColor: Color
    val onSelectedColor: Color
    val dismissColor: Color
    val onDismissColor: Color
    val onApplyColor: Color
    val applyColor: Color
    val barsColor: Color
    val onBarsColor: Color
}

@Immutable
private class DefaultLanguagePickerColors(
    override val contentColor: Color,
    override val onContentColor: Color,
    override val selectedColor: Color,
    override val onSelectedColor: Color,
    override val dismissColor: Color,
    override val onDismissColor: Color,
    override val onApplyColor: Color,
    override val applyColor: Color,
    override val barsColor: Color,
    override val onBarsColor: Color,
) : LanguagePickerColors

@Composable
fun languagePickerColors(
    contentColor: Color = MaterialTheme.colorScheme.surface,
    onContentColor: Color = MaterialTheme.colorScheme.onSurface,
    selectedColor: Color = MaterialTheme.colorScheme.secondary,
    onSelectedColor: Color = MaterialTheme.colorScheme.onSecondary,
    dismissColor: Color = MaterialTheme.colorScheme.surface,
    onDismissColor: Color = MaterialTheme.colorScheme.onSurface,
    onApplyColor: Color = MaterialTheme.colorScheme.onPrimary,
    applyColor: Color = MaterialTheme.colorScheme.primary,
    barsColor: Color = MaterialTheme.colorScheme.secondary,
    onBarsColor: Color = MaterialTheme.colorScheme.onSecondary,
): LanguagePickerColors =
    DefaultLanguagePickerColors(
        contentColor,
        onContentColor,
        selectedColor,
        onSelectedColor,
        dismissColor,
        onDismissColor,
        onApplyColor,
        applyColor,
        barsColor,
        onBarsColor,
    )

@Composable
fun LanguagePicker(
    headerText: String = "",
    languageOptions: List<LanguageOption>,
    onDismissRequest: () -> Unit = {},
    onApplyEvent: ((String) -> Unit)? = null,
    padding: PaddingValues = PaddingValues(15.dp, 10.dp),
    colors: LanguagePickerColors = languagePickerColors(),
    currentLanguage: String,
) {
    var selected by remember { mutableStateOf(currentLanguage) }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties =
            DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = false,
            ),
    ) {
        // Overlay semi-transparent
        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.80f))) {
            Card(
                modifier =
                    Modifier
                        .wrapContentHeight()
                        .padding(padding)
                        .width(400.dp)
                        .align(Alignment.Center), // Centre le Card dans le Box
            ) {
                Column(
                    modifier = Modifier.background(colors.barsColor),
                ) {
                    Header(headerText, padding, colors)
                    LanguagesLazyColumn(
                        languageOptions,
                        padding,
                        onValueChange = { selected = it },
                        selected = selected,
                        modifier = Modifier.weight(1f, false),
                        colors,
                    )
                    DecisionButtons(onDismissRequest, padding, onApplyEvent, selected, colors)
                }
            }
        }
    }
}

@Composable
fun LanguagesLazyColumn(
    languageOptions: List<LanguageOption>,
    padding: PaddingValues,
    onValueChange: (String) -> Unit,
    selected: String,
    modifier: Modifier = Modifier,
    colors: LanguagePickerColors,
) {
    val spacerSize = padding.calculateTopPadding()
    LazyColumn(
        modifier =
            Modifier
                .padding(0.dp)
                .then(modifier)
                .background(colors.contentColor),
    ) {
        item {
            for (languageOption in languageOptions) {
                Spacer(Modifier.height(spacerSize))
                LanguageButton(
                    selected = selected == languageOption.code,
                    onClick = { onValueChange(languageOption.code) },
                    languageOption,
                )
            }
            Spacer(Modifier.height(spacerSize))
        }
    }
}

@Composable
fun DecisionButtons(
    onDismissRequest: () -> Unit = {},
    padding: PaddingValues,
    onApplyEvent: ((String) -> Unit)? = null,
    selected: String,
    colors: LanguagePickerColors,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(colors.barsColor),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(
            modifier =
                Modifier
                    .weight(1f)
                    .defaultMinSize(1.dp, 60.dp)
                    .padding(padding),
            onClick = onDismissRequest,
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = colors.dismissColor,
                ),
        ) {
            Text(
                style = MaterialTheme.typography.titleMedium,
                text = "Annuler",
                color = colors.onDismissColor,
                fontWeight = FontWeight.Bold,
            )
        }
        Button(
            modifier =
                Modifier
                    .weight(1f)
                    .defaultMinSize(1.dp, 60.dp)
                    .padding(padding),
            onClick = {
                if (onApplyEvent != null) {
                    onApplyEvent(selected)
                }
            },
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = colors.applyColor,
                ),
        ) {
            Text(
                style = MaterialTheme.typography.titleMedium,
                text = "Confirmer",
                color = colors.onApplyColor,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun Header(
    text: String,
    padding: PaddingValues,
    colors: LanguagePickerColors,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(colors.barsColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            style = MaterialTheme.typography.titleLarge,
            text = text,
            color = colors.onBarsColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(padding),
        )
    }
}

@Composable
fun LanguageButton(
    selected: Boolean,
    onClick: () -> Unit,
    languageOption: LanguageOption,
    padding: PaddingValues = PaddingValues(10.dp),
    colors: LanguagePickerColors = languagePickerColors(),
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .then(
                    if (!selected) {
                        Modifier.background(Color.Transparent)
                    } else {
                        Modifier.background(colors.selectedColor)
                    },
                ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.padding(padding))

        Image(
            painter = painterResource(id = languageOption.icon),
            contentDescription = languageOption.contentDescription,
            modifier =
                Modifier
                    .clip(shape = CircleShape)
                    .size(70.dp)
                    .padding(padding),
        )

        Text(
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f),
            text = languageOption.name,
            color = if (selected) colors.onSelectedColor else colors.onContentColor,
        )
        if (selected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = colors.onSelectedColor,
                modifier =
                    Modifier
                        .size(50.dp)
                        .padding(padding),
            )
            Spacer(modifier = Modifier.padding(padding))
        }
    }
}
