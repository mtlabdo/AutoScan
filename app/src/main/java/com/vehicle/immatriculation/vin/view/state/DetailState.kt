package com.vehicle.immatriculation.vin.view.state

import com.vehicle.immatriculation.vin.model.Detail

sealed interface DetailState {

    data object Loading : DetailState

    data class Success(
        val detail: Detail
    ) : DetailState

    data class Error(val error: String) : DetailState
}