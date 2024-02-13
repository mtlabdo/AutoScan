package com.vehicule.immatriculation.histo.data.database

import com.vehicule.immatriculation.histo.data.database.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

interface AppDbService {

    fun getAllHistory(): Flow<List<HistoryEntity>>

    fun addHistoryItem(plateNumber : String)

    fun deleteHistoryItem(id : Int)
}