package com.vehicule.immatriculation.histo.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vehicule.immatriculation.histo.R
import com.vehicule.immatriculation.histo.navigation.AppState
import com.vehicule.immatriculation.histo.ui.theme.AutoScanAppTheme
import com.vehicule.immatriculation.histo.ui.widget.BackUiComposable
import com.vehicule.immatriculation.histo.view.state.DetailState
import com.vehicule.immatriculation.histo.view.viewmodel.DetailViewModel
import com.vehicule.immatriculation.histo.dispatcher.DispatcherProvider
import com.vehicule.immatriculation.histo.model.Detail
import java.util.Locale


@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    coroutineDispatcher: DispatcherProvider,
    appState: AppState
) {

    Surface() {
        Scaffold(
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxHeight()
                ) { DetailScreenContent(viewModel, coroutineDispatcher, appState) }
            }
        )
    }

}

@Composable
fun DetailScreenContent(
    viewModel: DetailViewModel,
    coroutineDispatcher: DispatcherProvider,
    appState: AppState
) {

    val uiState by viewModel.viewState.collectAsStateWithLifecycle(
        initialValue = DetailState.Loading,
        context = coroutineDispatcher.main
    )



    when (uiState) {
        is DetailState.Loading -> {
            SearchPlateAnimation()
        }

        is DetailState.Success -> {
            val successState = uiState as DetailState.Success
            TopBarWithLogo(successState.detail) { appState.onBackClick() }
            HorizontalDivider(Modifier.padding(vertical = 4.dp, horizontal = 16.dp))
            VehicleDetailsScreen(successState.detail) { }
        }

        is DetailState.Error -> {
            val errorState = uiState as DetailState.Error
            TopBarWithLogo(null) { appState.onBackClick() }
            ErrorScreen(errorState.error)
        }

    }
}

@Composable
fun VehicleDetailsScreen(detail: Detail, onBackClicked: () -> Unit) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        VehicleBasicInfoSection(detail)
        VehicleTechnicalDetailsSection(detail)
        VehicleIdentificationSection(detail)
        VehicleAdditionalDetailsSection(detail)
    }
}

@Composable
fun TopBarWithLogo(detail: Detail?, onBackClicked: () -> Unit) {
    val context = LocalContext.current // Get local context to access resources

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackUiComposable {
            onBackClicked()
        }
        Spacer(modifier = Modifier.width(16.dp))
        detail?.let {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "${detail.marque} ${detail.modele}",
                    style = MaterialTheme.typography.headlineSmall,

                    )
                detail.date1erCirFr?.let {
                    Text(
                        context.getString(R.string.first_circulation, it),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .error(R.drawable.fiat_logo)
                    .data(detail.logoMarque)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.car_placeholder),
                contentDescription = "Logo de la marque",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(72.dp)
            )
        }

    }


}

@Composable
fun VehicleBasicInfoSection(detail: Detail) {
    val context = LocalContext.current // Get local context to access resources
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp), // Bords arrondis pour la Card
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp) // Légère augmentation de l'élévation pour plus de profondeur
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            TitleSection(
                title = context.getString(R.string.basic_information),
                icon = R.drawable.ic_infos_base
            )
            Spacer(modifier = Modifier.height(16.dp)) // Espace après le titre

            DetailItem(
                context.getString(R.string.registration),
                detail.immat?.uppercase(Locale.getDefault()),
            )
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.brand), detail.marque)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.model), detail.modele)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(
                context.getString(R.string.first_registration_date),
                detail.date1erCirFr,
            )
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.gearbox), detail.boiteVitesse)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.number_of_doors), detail.nbPortes)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.color), detail.couleur)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.fuel), detail.energieNGC)
        }
    }
}

@Composable
fun VehicleTechnicalDetailsSection(detail: Detail) {
    val context = LocalContext.current // Get local context to access resources

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Définit l'élévation de la Card
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TitleSection(
                title = context.getString(R.string.technical_information),
                icon = R.drawable.ic_info_tech
            )
            Spacer(modifier = Modifier.height(16.dp)) // Espace après le titre
            DetailItem(context.getString(R.string.fiscal_power), detail.puisFisc)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.real_fiscal_power), detail.puisFiscReel)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.body_type), detail.carrosserieCG)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.weight), detail.poids)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.cylinders), detail.cylindres)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.energy), detail.energie)
        }
    }
}

@Composable
fun VehicleIdentificationSection(detail: Detail) {
    val context = LocalContext.current // Get local context to access resources

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Définit l'élévation de la Card
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TitleSection(
                title = context.getString(R.string.vehicle_identification),
                icon = R.drawable.ic_car_id
            )
            Spacer(modifier = Modifier.height(16.dp)) // Espace après le titre
            DetailItem(context.getString(R.string.genre), detail.genreVCGNGC)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.vin), detail.vin)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.type_mine), detail.typeMine)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.sra_commercial), detail.sraCommercial)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.sra_group), detail.sraGroup)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.sra_id), detail.sraId)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.engine_code), detail.codeMoteur)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.k_type), detail.kType)
        }
    }
}

@Composable
fun VehicleAdditionalDetailsSection(detail: Detail) {
    val context = LocalContext.current // Get local context to access resources

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Définit l'élévation de la Card
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TitleSection(
                title = context.getString(R.string.additional_information),
                icon = R.drawable.ic_car_plus
            )
            Spacer(modifier = Modifier.height(16.dp)) // Espace après le titre
            DetailItem(context.getString(R.string.number_of_passengers), detail.nrPassagers)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.collection), detail.collection)
        }
    }
}


@Composable
fun TitleSection(title: String, @DrawableRes icon: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
            tint = Color.Unspecified,
            modifier = Modifier.size(32.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
    }

}

@Composable
fun DetailItem(label: String, value: String?, iconId: Int? = null) {
    val context = LocalContext.current // Get local context to access resources
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        iconId?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = label,
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value ?: context.getString(R.string.not_available),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun SearchPlateAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("search_anim.json"))

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            speed = 1.2f,
            iterations = LottieConstants.IterateForever,
        )
    }
}


@Composable
fun ErrorScreen(errorMessage: String) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("error.json"))

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(
                composition = composition,
                speed = 1.2f,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(240.dp) // Ajustez la taille selon vos besoins
            )
            Text(
                text = errorMessage,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AutoScanAppTheme {
        VehicleDetailsScreen(detail = detail) {}
    }
}


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