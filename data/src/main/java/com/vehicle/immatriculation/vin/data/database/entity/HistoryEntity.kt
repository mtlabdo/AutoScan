package com.vehicle.immatriculation.vin.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "history",
    indices = [Index(
        value = arrayOf("plateNumber"),
        unique = true
    )]
)
data class HistoryEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,

    @ColumnInfo("plateNumber")
    val plateNumber: String,

    )