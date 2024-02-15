package com.vehicle.immatriculation.vin.data.database

import com.vehicle.immatriculation.vin.data.database.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

class AppDatabaseService(
    private val database: AppDatabase,
) : AppDbService {

    override fun getAllHistory(): Flow<List<HistoryEntity>> {
        return database.getVehicleDao().getAllHistory()
    }

    override fun addHistoryItem(plateNumber: String) {
        return database.getVehicleDao().addHistoryItemById(HistoryEntity(plateNumber = plateNumber))
    }

    override fun deleteHistoryItem(id: Int) {
        return database.getVehicleDao().deleteHistoryItemById(id)
    }
}