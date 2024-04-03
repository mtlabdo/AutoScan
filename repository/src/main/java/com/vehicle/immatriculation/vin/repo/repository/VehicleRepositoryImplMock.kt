/*
package com.vehicle.immatriculation.vin.repo.repository

import com.vehicle.immatriculation.vin.data.database.AppDbService
import com.vehicle.immatriculation.vin.model.Detail
import com.vehicle.immatriculation.vin.model.History
import com.vehicle.immatriculation.vin.data.remote.api.ApiInterface
import com.vehicle.immatriculation.vin.model.Consult
import com.vehicle.immatriculation.vin.repository.DataState
import com.vehicle.immatriculation.vin.repository.ErrorHolder
import com.vehicle.immatriculation.vin.repository.VehicleRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class VehicleRepositoryImplMock @Inject internal constructor(
    private val database: AppDbService,
    private val vehicleService: ApiInterface
) : VehicleRepository {
    private var listMock = mutableListOf<History>(
    )


    private val detail = Consult(
       id = "",
        status = "processing"
    )

    override fun getHistory(): Flow<DataState<List<History>>> = flow {
        try {
            emit(DataState.Success(listMock))
        } catch (generalException: Exception) {
            emit(DataState.Failure(ErrorHolder.Unknown("Un problème est survenu lors de la récuperation de l'historique des recherches")))
        }
    }

    override fun deleteHistoryItem(id: Int) {
        listMock = listMock.filterNot { it.id == id }.toMutableList()
    }

    override fun getDetailByPlate(plate: String): Flow<DataState<Consult>> = flow {
        delay(3000)
        emit(DataState.Success(detail))
    }

    override fun addHistoryItem(plate: String) {
        listMock.add(History((0..50000).random(), plate))
    }
}


*/
