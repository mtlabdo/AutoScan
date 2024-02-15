package com.vehicle.immatriculation.vin.di.module

import com.vehicle.immatriculation.vin.dispatcher.DefaultDispatcherProvider
import com.vehicle.immatriculation.vin.dispatcher.DispatcherProvider
import com.vehicle.immatriculation.vin.data.Constant
import com.vehicle.immatriculation.vin.di.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCoroutineDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()


    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = Constant.API_BASE_URL
}