package com.vehicle.immatriculation.vin.data.database

import com.vehicle.immatriculation.vin.data.database.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

interface AppDbService {

    fun getAllHistory(): Flow<List<HistoryEntity>>

    fun addHistoryItem(plateNumber : String)

    fun deleteHistoryItem(id : Int)
}