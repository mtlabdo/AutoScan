package com.vehicle.immatriculation.vin.repository

import com.vehicle.immatriculation.vin.model.Consult
import com.vehicle.immatriculation.vin.model.Detail
import com.vehicle.immatriculation.vin.model.History
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    fun getConsultByPlate(plate: String): Flow<DataState<Consult>>

    fun getDetailByConsultId(id: String, plate: String): Flow<DataState<Detail>>

    fun getHistory(): Flow<DataState<List<History>>>

    fun addHistoryItem(plate: String)

    fun deleteHistoryItem(id: Int)
}