package com.vehicle.immatriculation.vin.di.module

import com.vehicle.immatriculation.vin.repo.repository.VehicleRepositoryImpl
import com.vehicle.immatriculation.vin.repository.VehicleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    /*    @Binds
    abstract fun vehicleRepository(recipesRepository: VehicleRepositoryImpl): VehicleRepository
     */
    @Binds
    abstract fun vehicleRepository(recipesRepository: VehicleRepositoryImpl): VehicleRepository
}
