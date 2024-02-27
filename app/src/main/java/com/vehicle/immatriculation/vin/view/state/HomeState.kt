package com.vehicle.immatriculation.vin.view.state

import com.vehicle.immatriculation.vin.model.History

sealed interface HomeState {
    data object Loading : HomeState

    data class Success(
        val historyList: List<History>,
    ) : HomeState

    data class Error(val error: String) : HomeState
}
