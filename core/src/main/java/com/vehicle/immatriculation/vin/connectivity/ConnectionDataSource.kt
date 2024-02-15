package com.vehicle.immatriculation.vin.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectionDataState {
    fun observeIsConnected(): Flow<ConnectionState>
}
