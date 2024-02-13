package com.vehicule.immatriculation.histo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vehicule.immatriculation.histo.data.database.entity.HistoryEntity
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