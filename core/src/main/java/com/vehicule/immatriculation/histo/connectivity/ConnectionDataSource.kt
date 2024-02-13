package com.vehicule.immatriculation.histo.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectionDataState {
    fun observeIsConnected(): Flow<ConnectionState>
}
