package com.vehicle.immatriculation.vin.data.remote.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.vehicle.immatriculation.vin.model.Detail

@Keep
data class ApiResponse(
    @SerializedName("input") val input: String,
    @SerializedName("short_info") val shortInfo: ShortInfo,
    @SerializedName("share") val share: String,
    @SerializedName("tags") val tags: Tags,
    @SerializedName("user_preferences") val userPreferences: UserPreferences,
    @SerializedName("content") val content: List<Content>,
    @SerializedName("ads") val ads: Ads,
    @SerializedName("message") val message: String
)

@Keep
data class ShortInfo(
    @SerializedName("brand") val brand: String,
    @SerializedName("model") val model: String,
    @SerializedName("year") val year: Int
)

@Keep
data class Tags(
    @SerializedName("list") val list: List<TagItem>
)

@Keep
data class TagItem(
    @SerializedName("name") val name: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("action") val action: Action
)

@Keep
data class Action(
    @SerializedName("type") val type: String,
    @SerializedName("value") val value: String
)

@Keep
data class UserPreferences(
    @SerializedName("_type") val type: String,
    @SerializedName("_id") val id: String,
    @SerializedName("is_favorite") val isFavorite: Boolean,
    @SerializedName("note") val note: String
)

@Keep
data class Content(
    @SerializedName("_id") val id: String,
    @SerializedName("_type") val type: String,
    val title: String? = null,
    @SerializedName("title_icon") val titleIcon: String? = null,
    val fields: List<Field>? = null,
    val token: String? = null,
    val subtitle: String? = null,
    val records: List<Record>? = null,
    val left: NeighborsItem? = null,
    val right: NeighborsItem? = null
)

@Keep
data class Field(
    val label: String,
    val value: String
)

@Keep
data class Record(
    val date: String,
    val name: String
)

@Keep
data class NeighborsItem(
    val id: String,
    val brand: String,
    val model: String,
    val year: Int
)

@Keep
data class Ads(
    @SerializedName("on_end") val onEnd: Boolean
)


@Keep
data class VehicleDetailNetwork(
    @SerializedName("data") val data: DetailNetwork
)

@Keep
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


fun ApiResponse.toDetail(): Detail {
    val content = this.content

    // Finding the content with title "Identification"
    val identificationContent = content.find { it.title == "Identification" }
    // Extracting relevant fields from the identification content
    val immat = identificationContent?.fields?.find { it.label == "Numéro d'immatriculation" }?.value
    val vin = identificationContent?.fields?.find { it.label == "Numéro d'Identification Véhicule (VIN)" }?.value
    val Finition = identificationContent?.fields?.find { it.label == "Finition" }?.value
    val Carrosserie = identificationContent?.fields?.find { it.label == "Carrosserie" }?.value
    val Type = identificationContent?.fields?.find { it.label == "Type" }?.value
    val Places_assises = identificationContent?.fields?.find { it.label == "Places assises" }?.value
    val age = identificationContent?.fields?.find { it.label == "Âge du véhicule" }?.value

    val motorisationContent = content.find { it.title == "Motorisation"}?.fields
    val cylindree = motorisationContent?.find { it.label == "Cylindrée" }?.value
    val puissance = motorisationContent?.find { it.label == "Puissance" }?.value
    val puissance_fisc = motorisationContent?.find { it.label == "Puissance fiscale" }?.value
    val cylindres = motorisationContent?.find { it.label == "Cylindres" }?.value

    val carburant = content.find { it.title == "Carburant" }?.fields?.find { it.label == "Type" }?.value

    val first_circ = content.find { it.title == "Historique du véhicule" }?.records?.find { it.name == "Mise en circulation" }?.date

    val boite = content.find { it.title == "Transmission" }?.fields?.find { it.label == "Boîte de vitesses" }?.value


    // Constructing the Detail object
    return Detail(
        immat = immat,
        co2 = "119",
        marque = shortInfo.brand,
        modele = shortInfo.model,
        carrosserieCG = Carrosserie,
        date1erCirFr = first_circ,
        energie = cylindres,
        energieNGC = carburant,
        genreVCG = 1,
        genreVCGNGC = Type,
        puisFisc = puissance_fisc,
        date1erCirUs = first_circ,
        collection = "non",
        date30 = "-",
        vin = vin,
        boiteVitesse = boite,
        puisFiscReel = puissance,
        nrPassagers = Places_assises,
        nbPortes = "-",
        typeMine = "-",
        couleur = "-",
        poids = "-",
        cylindres = cylindree,
        sraId = "-",
        sraGroup = "-",
        sraCommercial = "-",
        logoMarque = "https=//api.apiplaqueimmatriculation.com/logos_marques/fiat.png",
        codeMoteur = "",
        kType = "-",

    )
}
