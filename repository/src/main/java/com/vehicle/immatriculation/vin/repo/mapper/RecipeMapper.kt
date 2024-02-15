package com.vehicle.immatriculation.vin.repo.mapper


import com.vehicle.immatriculation.vin.model.Detail
import com.vehicle.immatriculation.vin.model.History
import com.vehicle.immatriculation.vin.data.database.entity.HistoryEntity
import com.vehicle.immatriculation.vin.data.remote.model.DetailNetwork


////////////////////////////////
fun DetailNetwork.toDataModel() = Detail(
    erreur = erreur,
    immat = immat,
    co2 = co2?.toString(),
    energie = energie?.toString(),
    energieNGC = energieNGC,
    genreVCG = genreVCG?.toLong(),
    genreVCGNGC = genreVCGNGC,
    puisFisc = puisFisc?.toString(),
    carrosserieCG = carrosserieCG,
    marque = marque,
    modele = modele,
    date1erCirUs = date1erCir_us,
    date1erCirFr = date1erCir_fr,
    collection = collection,
    date30 = date30,
    vin = vin,
    boiteVitesse = boite_vitesse,
    puisFiscReel = puisFiscReel?.toString(),
    nrPassagers = nr_passagers?.toString(),
    nbPortes = nb_portes?.toString(),
    typeMine = type_mine,
    couleur = couleur,
    poids = poids,
    cylindres = cylindres?.toString(),
    sraId = sra_id,
    sraGroup = sra_group?.toString(),
    sraCommercial = sra_commercial,
    logoMarque = logo_marque,
    codeMoteur = code_moteur,
    kType = k_type?.toString()
)
////////////////////////////////

fun HistoryEntity.toDataModel() = History(
    id = id,
    plateNumber = plateNumber,
)


fun List<HistoryEntity>.toListDataModel() = this.map {
    it.toDataModel()
}
