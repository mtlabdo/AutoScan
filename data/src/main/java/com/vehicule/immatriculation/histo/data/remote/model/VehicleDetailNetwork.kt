package com.vehicule.immatriculation.histo.data.remote.model

import com.google.gson.annotations.SerializedName


data class VehicleDetailNetwork(
    @SerializedName("data") val data: DetailNetwork
)

data class DetailNetwork(
    @SerializedName("erreur") val erreur: String? = null,
    @SerializedName("immat") val immat: String? = null,
    @SerializedName("co2") val co2: Int? = null,
    @SerializedName("energie") val energie: Int? = null,
    @SerializedName("energieNGC") val energieNGC: String? = null,
    @SerializedName("genreVCG") val genreVCG: Int? = null,
    @SerializedName("genreVCGNGC") val genreVCGNGC: String? = null,
    @SerializedName("puisFisc") val puisFisc: Int? = null,
    @SerializedName("carrosserieCG") val carrosserieCG: String? = null,
    @SerializedName("marque") val marque: String? = null,
    @SerializedName("modele") val modele: String? = null,
    @SerializedName("date1erCir_us") val date1erCir_us: String? = null,
    @SerializedName("date1erCir_fr") val date1erCir_fr: String? = null,
    @SerializedName("collection") val collection: String? = null,
    @SerializedName("date30") val date30: String? = null,
    @SerializedName("vin") val vin: String? = null,
    @SerializedName("boite_vitesse") val boite_vitesse: String? = null,
    @SerializedName("puisFiscReel") val puisFiscReel: Int? = null,
    @SerializedName("nr_passagers") val nr_passagers: Int? = null,
    @SerializedName("nb_portes") val nb_portes: Int? = null,
    @SerializedName("type_mine") val type_mine: String? = null,
    @SerializedName("couleur") val couleur: String? = null,
    @SerializedName("poids") val poids: String? = null,
    @SerializedName("cylindres") val cylindres: Int? = null,
    @SerializedName("sra_id") val sra_id: String? = null,
    @SerializedName("sra_group") val sra_group: Int? = null,
    @SerializedName("sra_commercial") val sra_commercial: String? = null,
    @SerializedName("logo_marque") val logo_marque: String? = null,
    @SerializedName("code_moteur") val code_moteur: String? = null,
    @SerializedName("k_type") val k_type: Int? = null
)
