package com.vehicle.immatriculation.vin.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vehicle.immatriculation.vin.data.database.dao.VehicleDao
import com.vehicle.immatriculation.vin.data.database.entity.HistoryEntity


@Database(entities = [HistoryEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getVehicleDao(): VehicleDao

    companion object {
        fun buildDatabase(application: Application, dbName: String) =
            Room.databaseBuilder(application, AppDatabase::class.java, dbName).fallbackToDestructiveMigration().build()
    }
}

