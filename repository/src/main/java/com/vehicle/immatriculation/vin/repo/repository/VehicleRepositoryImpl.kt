package com.vehicle.immatriculation.vin.repo.repository

import com.vehicle.immatriculation.vin.data.Constant
import com.vehicle.immatriculation.vin.data.database.AppDbService
import com.vehicle.immatriculation.vin.repo.mapper.toDataModel
import com.vehicle.immatriculation.vin.repo.mapper.toListDataModel
import com.vehicle.immatriculation.vin.model.History
import com.vehicle.immatriculation.vin.data.remote.api.ApiInterface
import com.vehicle.immatriculation.vin.data.remote.model.ConsultReq
import com.vehicle.immatriculation.vin.data.remote.util.getResponse
import com.vehicle.immatriculation.vin.model.Consult
import com.vehicle.immatriculation.vin.model.Detail
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


    override fun getConsultByPlate(plate: String): Flow<DataState<Consult>> = flow {
        val requestBody = ConsultReq(
            placa_niv = plate,
            callback = Constant.API_CALLBACK_URL
        )
        val consultResponse = vehicleService.fetchConsult(requestBody).getResponse()
        val dataState = if (!consultResponse?.id.isNullOrEmpty()) {
            DataState.Success(consultResponse!!.toDataModel())
        } else if (consultResponse?.detail?.type == "value_error" && consultResponse.detail?.msg.isNullOrBlank()) {
            DataState.Failure(ErrorHolder.Unknown(consultResponse.detail?.msg.toString()))
        } else {
            DataState.Failure(ErrorHolder.Unknown("Imposible encontrar la información de la placa : $plate"))
        }
        emit(dataState)
    }.catch {
        emit(DataState.Failure(ErrorHolder.Unknown("Imposible encontrar la información de la placa : $plate")))
    }


    override fun getDetailByConsultId(id: String, plate: String): Flow<DataState<Detail>> = flow {
        val detailResponse = vehicleService.fetchDetail(id).getResponse()
        val dataState = if (detailResponse?.id != null && detailResponse.result != null) {
            DataState.Success(detailResponse.toDataModel())
        } else if (detailResponse?.id != null && !detailResponse.placas.isNullOrBlank()) {
            DataState.Processing(id)
        } else if (!detailResponse?.detail.isNullOrBlank()) {
            DataState.Failure(
                ErrorHolder.Unknown(
                    detailResponse?.detail
                        ?: "Imposible encontrar la información de la placa : $plate"
                )
            )
        } else {
            DataState.Failure(ErrorHolder.Unknown("Imposible encontrar la información de la placa : $plate"))
        }
        emit(dataState)
    }.catch {
        emit(DataState.Failure(ErrorHolder.Unknown("Imposible encontrar la información de la placa : $plate")))
    }

    override fun getHistory(): Flow<DataState<List<History>>> = flow {
        try {
            val historyList: List<History> = database.getAllHistory().first().toListDataModel()
            emit(DataState.Success(historyList))
        } catch (generalException: Exception) {
            emit(DataState.Failure(ErrorHolder.Unknown("Se ha producido un problema al recuperar el historial de búsquedas.")))
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
