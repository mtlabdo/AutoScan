package com.vehicule.immatriculation.histo.view.state

import androidx.annotation.StringRes
import com.vehicule.immatriculation.histo.ui.widget.langue.LanguageOption

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
