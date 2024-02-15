package com.vehicle.immatriculation.vin.view.viewmodel

import androidx.lifecycle.ViewModel
import com.vehicle.immatriculation.vin.R
import com.vehicle.immatriculation.vin.ui.widget.langue.LanguageOption
import com.vehicle.immatriculation.vin.view.state.CardUiState
import com.vehicle.immatriculation.vin.view.state.SettingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class SettingViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    init {
        getCards()
        getLanguages()
    }
    private fun getCards() {
        _state.update {
            it.copy(
                settingCards = listOf(
                    CardUiState(
                        id = 1,
                        R.drawable.ic_langue, R.string.language_card_title, null, null,
                    ), CardUiState(
                        id = 3,
                        R.drawable.ic_dark_light, R.string.dark_mode_card_title, null, null
                    ),
                    CardUiState(
                        id = 4,
                        R.drawable.ic_about, R.string.about_us_card_title, null, null
                    ), CardUiState(
                        id = 5,
                        R.drawable.ic_privacy, R.string.privacy_policy_card_title, null, null
                    ), CardUiState(
                        id = 6,
                        R.drawable.ic_term, R.string.terms_of_service_card_title, null, null
                    ), CardUiState(
                        id = 7,
                        R.drawable.ic_term_use, R.string.terms_of_use_card_title, null, null
                    )
                )
            )
        }
    }


    private fun getLanguages() {
        _state.update {
            it.copy(
                languages = listOf(
                    LanguageOption(
                        icon = R.drawable.ic_fr,
                        name = "Francais",
                        code = "fr"
                    ),
                    LanguageOption(
                        icon = R.drawable.ic_en,
                        name = "English",
                        code = "en"
                    ),
                    LanguageOption(
                        icon = R.drawable.ic_deu,
                        name = "Deutsch",
                        code = "de"
                    ),
                    LanguageOption(
                        icon = R.drawable.ic_spanish,
                        name = "Spanish",
                        code = "es"
                    )
                )
            )
        }
    }
}