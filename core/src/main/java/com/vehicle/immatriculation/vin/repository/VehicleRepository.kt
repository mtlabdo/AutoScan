package com.vehicle.immatriculation.vin.repository

import com.vehicle.immatriculation.vin.model.Detail
import com.vehicle.immatriculation.vin.model.History
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    fun getDetailByPlate(plate: String): Flow<DataState<Detail>>

    fun getHistory(): Flow<DataState<List<History>>>

    fun addHistoryItem(plate: String)

    fun deleteHistoryItem(id: Int)

    suspend fun sendFeedback(feed: String)
}