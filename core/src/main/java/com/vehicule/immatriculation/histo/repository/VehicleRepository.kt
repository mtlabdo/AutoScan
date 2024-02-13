package com.vehicule.immatriculation.histo.repository

import com.vehicule.immatriculation.histo.model.Detail
import com.vehicule.immatriculation.histo.model.History
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    fun getDetailByPlate(plate: String): Flow<DataState<Detail>>

    fun getHistory(): Flow<DataState<List<History>>>

    fun addHistoryItem(plate: String)

    fun deleteHistoryItem(id: Int)
}