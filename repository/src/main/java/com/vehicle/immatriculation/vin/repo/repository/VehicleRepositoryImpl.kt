package com.vehicle.immatriculation.vin.repo.repository

import com.vehicle.immatriculation.vin.data.database.AppDbService
import com.vehicle.immatriculation.vin.repo.mapper.toDataModel
import com.vehicle.immatriculation.vin.repo.mapper.toListDataModel
import com.vehicle.immatriculation.vin.model.Detail
import com.vehicle.immatriculation.vin.model.History
import com.vehicle.immatriculation.vin.data.remote.api.ApiInterface
import com.vehicle.immatriculation.vin.data.remote.api.ApiMailInterface
import com.vehicle.immatriculation.vin.data.remote.api.Mail
import com.vehicle.immatriculation.vin.data.remote.model.toDetail
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
    private val vehicleService: ApiInterface,
    private val mailService: ApiMailInterface
) : VehicleRepository {


    override fun getDetailByPlate(plate: String): Flow<DataState<Detail>> = flow {
        val recipeDetailResponse = vehicleService._getDetail(plate).getResponse()
        val response = recipeDetailResponse?.content
        val dataState = if (!response?.get(0)?.id.isNullOrEmpty()) {
            DataState.Success(recipeDetailResponse!!.toDetail())
        } else if (recipeDetailResponse?.message?.isNotEmpty() == true) {
            DataState.Failure(ErrorHolder.Unknown(recipeDetailResponse?.message!!))
        } else {
            //DataState.Failure(ErrorHolder.Unknown("Impossible de trouver les informations de la plaque suivante : $plate"))
            DataState.Failure(ErrorHolder.Unknown("Impossible de trouver les informations de la plaque suivante : $plate {${response.toString()}}}"))
        }
        emit(dataState)
    }.catch {
        emit(
            DataState.Failure(
                ErrorHolder.Unknown(
                    "Impossible de trouver les informations de la plaque suivante : $plate"
                )
            )
        )
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

    override suspend fun sendFeedback(feed: String) {
        val mail = Mail(
            body = feed
        )
        try {
            val sendFeedBAckRes = mailService.sendFeedBack(mail).getResponse()
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
