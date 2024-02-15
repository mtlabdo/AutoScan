package com.vehicle.immatriculation.vin.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vehicle.immatriculation.vin.data.database.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Query("SELECT * FROM history")
    fun getAllHistory(): Flow<List<HistoryEntity>>


    @Insert
    fun addHistoryItemById(item: HistoryEntity)

    @Query("DELETE FROM history WHERE id = :id")
    fun deleteHistoryItemById(id: Int)
}