package com.vehicule.immatriculation.histo.repo.repository

import com.vehicule.immatriculation.histo.data.database.AppDbService
import com.vehicule.immatriculation.histo.repo.mapper.toDataModel
import com.vehicule.immatriculation.histo.repo.mapper.toListDataModel
import com.vehicule.immatriculation.histo.model.Detail
import com.vehicule.immatriculation.histo.model.History
import com.vehicule.immatriculation.histo.data.remote.api.ApiInterface
import com.vehicule.immatriculation.histo.data.remote.util.getResponse
import com.vehicule.immatriculation.histo.repository.DataState
import com.vehicule.immatriculation.histo.repository.ErrorHolder
import com.vehicule.immatriculation.histo.repository.VehicleRepository
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
            DataState.Failure(ErrorHolder.Unknown("Impossible de trouver les informations de la plaque suivante : $plate"))
        }
        emit(dataState)
    }.catch {
        emit(DataState.Failure(ErrorHolder.Unknown("Impossible de trouver les informations de la plaque suivante : $plate")))
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
