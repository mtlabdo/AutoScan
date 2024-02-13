package com.vehicule.immatriculation.histo.di.module

import com.vehicule.immatriculation.histo.repository.VehicleRepository
import com.vehicule.immatriculation.histo.repo.repository.VehicleRepositoryImplMock
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
    abstract fun vehicleRepository(recipesRepository: VehicleRepositoryImplMock): VehicleRepository
}
