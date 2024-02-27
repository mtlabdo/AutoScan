package com.vehicle.immatriculation.vin.repo.repository

import com.vehicle.immatriculation.vin.data.database.AppDbService
import com.vehicle.immatriculation.vin.repo.mapper.toDataModel
import com.vehicle.immatriculation.vin.repo.mapper.toListDataModel
import com.vehicle.immatriculation.vin.model.Detail
import com.vehicle.immatriculation.vin.model.History
import com.vehicle.immatriculation.vin.data.remote.api.ApiInterface
import com.vehicle.immatriculation.vin.data.remote.util.getResponse
import com.vehicle.immatriculation.vin.repository.DataState
import com.vehicle.immatriculation.vin.repository.ErrorHolder
import com.vehicle.immatriculation.vin.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleRepositoryImpl @Inject internal constructor(
    private val database: AppDbService,
    private val vehicleService: ApiInterface
) : VehicleRepository {


    override fun getDetailByPlate(plate: String): Flow<DataState<Detail>> = flow {
        val recipeDetailResponse = vehicleService.getDetail(plate).getResponse()
        val response = recipeDetailResponse?.data
        val dataState = if (!response?.immat.isNullOrEmpty()) {
            DataState.Success(response!!.toDataModel())
        } else if (!response?.erreur.isNullOrEmpty()) {
            DataState.Failure(ErrorHolder.Unknown(recipeDetailResponse?.data?.erreur.toString()))
        } else {
            //DataState.Failure(ErrorHolder.Unknown("Impossible de trouver les informations de la plaque suivante : $plate"))
            DataState.Failure(ErrorHolder.Unknown("Impossible de trouver les informations de la plaque suivante : $plate {${response.toString()}}}"))
        }
        emit(dataState)
    }.catch {
        emit(DataState.Failure(ErrorHolder.Unknown("Impossible de trouver les informations de la plaque suivante : $plate {${it.printStackTrace().toString()}}")))
    }

    override fun getHistory(): Flow<DataState<List<History>>> = flow {
        try {
            val historyList: List<History> = database.getAllHistory().first().toListDataModel()
            emit(DataState.Success(historyList))
        } catch (generalException: Exception) {
            emit(DataState.Failure(ErrorHolder.Unknown("Un problème est survenu lors de la récuperation de l'historique des recherches")))
        }
    }

    override fun deleteHistoryItem(id: Int) {
        try {
            database.deleteHistoryItem(id)
        } catch (generalException: Exception) {
            generalException.printStackTrace()
        }
    }

    override fun addHistoryItem(plate: String) {
        try {
            database.addHistoryItem(plate)
        } catch (generalException: Exception) {
            generalException.printStackTrace()
        }
    }

}
