package com.vehicle.immatriculation.vin.view.state

import androidx.annotation.StringRes
import com.vehicle.immatriculation.vin.ui.widget.langue.LanguageOption

data class SettingsState(
    val settingCards: List<CardUiState> = emptyList(),
    val languages: List<LanguageOption> = emptyList()
)


data class CardUiState(
    val id: Int? = 0,
    val icon: Int,
    @StringRes val settingName: Int,
    @StringRes val subTitle: Int? = null,
    var defaultBox: Unit?,
)
