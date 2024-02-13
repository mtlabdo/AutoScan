package com.vehicule.immatriculation.histo.view.viewmodel

import androidx.lifecycle.viewModelScope
import com.vehicule.immatriculation.histo.dispatcher.DispatcherProvider
import com.vehicule.immatriculation.histo.repository.VehicleRepository
import com.vehicule.immatriculation.histo.view.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel<HomeState>() {

    override val initialViewState = HomeState.Loading

    private var syncJob: Job? = null

    init {
        getHistory()
    }

    private fun getHistory() {
        if (syncJob?.isActive == true) return
        try {
            syncJob = vehicleRepository.getHistory()
                .distinctUntilChanged()
                .map {
                    it.onSuccess { plates ->
                        updateViewState(HomeState.Success(plates))
                    }
                    it.onFailure { error ->
                        updateViewState(HomeState.Error(error.message))
                    }
                }
                .onStart {
                    updateViewState(HomeState.Loading)
                }
                .flowOn(dispatcherProvider.io)
                .launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
            updateViewState(HomeState.Error("Can not get history"))
        }
    }

    fun deleteHistoryItem(id: Int) {
        viewModelScope.launch(dispatcherProvider.io) {
            vehicleRepository.deleteHistoryItem(id)
        }
    }

    fun addHistoryItem(plateNumber: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            vehicleRepository.addHistoryItem(plateNumber)
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}