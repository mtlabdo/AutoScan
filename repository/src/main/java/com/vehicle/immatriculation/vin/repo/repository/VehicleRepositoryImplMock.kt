package com.vehicle.immatriculation.vin.repo.repository

import com.vehicle.immatriculation.vin.data.database.AppDbService
import com.vehicle.immatriculation.vin.model.Detail
import com.vehicle.immatriculation.vin.model.History
import com.vehicle.immatriculation.vin.data.remote.api.ApiInterface
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


    private val detail = Detail(
        immat = "CT851AA",
        co2 = "119",
        energie = "4",
        energieNGC = "ESSENCE",
        genreVCG = 1,
        genreVCGNGC = "VP",
        puisFisc = "4",
        carrosserieCG = "CI",
        marque = "FIAT",
        modele = "PANDA",
        date1erCirUs = "2009-06-02",
        date1erCirFr = "02-06-2009",
        collection = "non",
        date30 = "1989-06-30",
        vin = "ZFA16900001426851",
        boiteVitesse = "M",
        puisFiscReel = "60",
        nrPassagers = "4",
        nbPortes = "4",
        typeMine = "MFT1022E4419",
        couleur = "JAUNE CLAIR",
        poids = "860 kg",
        cylindres = "4",
        sraId = "FI04139",
        sraGroup = "27",
        sraCommercial = "ALESSI 1.2 8V",
        logoMarque = "https=//api.apiplaqueimmatriculation.com/logos_marques/fiat.png",
        codeMoteur = "",
        kType = "17628"
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

    override fun getDetailByPlate(plate: String): Flow<DataState<Detail>> = flow {
        delay(3000)
        emit(DataState.Success(detail))
    }

    override fun addHistoryItem(plate: String) {
        listMock.add(History((0..50000).random(), plate))
    }
}


