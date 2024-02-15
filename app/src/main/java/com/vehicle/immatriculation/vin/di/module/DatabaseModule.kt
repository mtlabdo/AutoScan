package com.vehicle.immatriculation.vin.di.module

import android.app.Application
import com.vehicle.immatriculation.vin.data.Constant
import com.vehicle.immatriculation.vin.data.database.AppDatabaseService
import com.vehicle.immatriculation.vin.data.database.AppDatabase
import com.vehicle.immatriculation.vin.data.database.AppDbService
import com.vehicle.immatriculation.vin.di.DbName

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
        @DbName dbName: String
    ): AppDatabase = AppDatabase.buildDatabase(application, dbName)

    @DbName
    @Provides
    fun provideDbName() = Constant.ROOM_DATABASE_NAME

    @Provides
    @Singleton
    fun provideAppDatabaseService(appDatabase: AppDatabase): AppDbService =
        AppDatabaseService(appDatabase)

}