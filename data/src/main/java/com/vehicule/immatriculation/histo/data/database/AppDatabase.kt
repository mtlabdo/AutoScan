package com.vehicule.immatriculation.histo.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vehicule.immatriculation.histo.data.database.dao.VehicleDao
import com.vehicule.immatriculation.histo.data.database.entity.HistoryEntity


@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getVehicleDao(): VehicleDao

    companion object {
        fun buildDatabase(application: Application, dbName: String) =
            Room.databaseBuilder(application, AppDatabase::class.java, dbName).build()
    }
}