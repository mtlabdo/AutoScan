package com.vehicle.immatriculation.vin.view.state

import com.vehicle.immatriculation.vin.model.Consult
import com.vehicle.immatriculation.vin.model.Detail

sealed interface DetailState {
    data object Loading : DetailState

    data class SuccessConsult(
        val consult: Consult,
    ) : DetailState


    data class SuccessDetail(
        val detail: Detail,
    ) : DetailState

    data class Error(val error: String) : DetailState
}
