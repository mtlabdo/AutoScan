package com.vehicle.immatriculation.vin.di.module

import android.content.Context
import android.net.ConnectivityManager
import com.vehicle.immatriculation.vin.BuildConfig
import com.vehicle.immatriculation.vin.connectivity.ConnectionDataState
import com.vehicle.immatriculation.vin.data.Constant
import com.vehicle.immatriculation.vin.data.remote.api.ApiInterface
import com.vehicle.immatriculation.vin.data.remote.retrofit.RetrofitService
import com.vehicle.immatriculation.vin.di.BaseUrl
import com.vehicle.immatriculation.vin.utils.ConnectionDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.MainScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideConnectivityManager(
        @ApplicationContext context: Context,
    ): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    fun providesConnectionState(connectivityManager: ConnectivityManager): ConnectionDataState =
        ConnectionDataSourceImpl(connectivityManager, MainScope())

    @Singleton
    @Provides
    fun provideGsonConverterFactory() = RetrofitService.getGsonConverterFactory()

    @Provides
    @Singleton
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) = RetrofitService.getHttpClient(httpLoggingInterceptor)

    @Provides
    @Singleton
    fun provideApiNetworkService(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): ApiInterface {
        return RetrofitService.getRetrofit<ApiInterface>(
            Constant.API_BASE_URL,
            okHttpClient,
            gsonConverterFactory,
        )
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        }
}
