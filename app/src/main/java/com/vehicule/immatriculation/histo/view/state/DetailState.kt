package com.vehicule.immatriculation.histo.view.state

import com.vehicule.immatriculation.histo.model.Detail

sealed interface DetailState {

    data object Loading : DetailState

    data class Success(
        val detail: Detail
    ) : DetailState

    data class Error(val error: String) : DetailState
}