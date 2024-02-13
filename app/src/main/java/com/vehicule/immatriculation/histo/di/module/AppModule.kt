package com.vehicule.immatriculation.histo.di.module

import com.vehicule.immatriculation.histo.dispatcher.DefaultDispatcherProvider
import com.vehicule.immatriculation.histo.dispatcher.DispatcherProvider
import com.vehicule.immatriculation.histo.data.Constant
import com.vehicule.immatriculation.histo.di.BaseUrl
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