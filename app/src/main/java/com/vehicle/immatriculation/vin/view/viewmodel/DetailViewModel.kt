package com.vehicle.immatriculation.vin.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.vehicle.immatriculation.vin.dispatcher.DispatcherProvider
import com.vehicle.immatriculation.vin.repository.VehicleRepository
import com.vehicle.immatriculation.vin.view.state.DetailState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class DetailViewModel @AssistedInject constructor(
    private val vehicleRepository: VehicleRepository,
    @Assisted val plateNumber: String,
    private val dispatcherProvider: DispatcherProvider

) : BaseViewModel<DetailState>() {

    override val initialViewState = DetailState.Loading


    private var syncJob: Job? = null

    init {
        getDetail()
    }

    private fun getDetail() {
        if (syncJob?.isActive == true) return
        try {
            syncJob = vehicleRepository.getDetailByPlate(plateNumber)
                .onStart { updateViewState(DetailState.Loading) }
                .onEach { delay(2000) }
                .map { it ->
                    it.onSuccess { detail ->
                        updateViewState(DetailState.Success(detail))
                    }
                    it.onFailure { error ->
                        updateViewState(DetailState.Error(error.message))
                    }
                }
                .flowOn(dispatcherProvider.io)
                .launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
            updateViewState(DetailState.Error("Can not get vehicle detail"))
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(plateNumber: String): DetailViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        private const val TAG = "DetailViewModel"

        fun provideFactory(
            assistedFactory: Factory, plateNumber: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(plateNumber) as T
            }
        }
    }
}