package com.vehicule.immatriculation.histo.view.state

import com.vehicule.immatriculation.histo.model.History


sealed interface HomeState {

    data object Loading : HomeState

    data class Success(
        val historyList: List<History>
    ) : HomeState

    data class Error(val error: String) : HomeState
}

