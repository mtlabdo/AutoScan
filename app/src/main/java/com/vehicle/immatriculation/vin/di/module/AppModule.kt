package com.vehicle.immatriculation.vin.di.module

import com.vehicle.immatriculation.vin.data.Constant
import com.vehicle.immatriculation.vin.di.BaseUrl
import com.vehicle.immatriculation.vin.di.MailBaseUrl
import com.vehicle.immatriculation.vin.dispatcher.DefaultDispatcherProvider
import com.vehicle.immatriculation.vin.dispatcher.DispatcherProvider
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

    @MailBaseUrl
    @Provides
    fun provideMailApiBaseUrl(): String = Constant.MAIL_API_BASE_URL
}
