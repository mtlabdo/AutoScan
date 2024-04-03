package com.vehicle.immatriculation.vin.ui.screens

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vehicle.immatriculation.vin.R
import com.vehicle.immatriculation.vin.dispatcher.DispatcherProvider
import com.vehicle.immatriculation.vin.model.Detail
import com.vehicle.immatriculation.vin.navigation.AppState
import com.vehicle.immatriculation.vin.ui.theme.AutoScanAppTheme
import com.vehicle.immatriculation.vin.ui.widget.BackUiComposable
import com.vehicle.immatriculation.vin.utils.FirebaseUtils
import com.vehicle.immatriculation.vin.view.state.DetailState
import com.vehicle.immatriculation.vin.view.viewmodel.DetailViewModel
import java.util.Locale

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    coroutineDispatcher: DispatcherProvider,
    appState: AppState,
) {
    Surface {
        Scaffold(
            content = { _ ->
                Column(
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxHeight(),
                ) { DetailScreenContent(viewModel, coroutineDispatcher, appState) }
            },
        )
    }
}

@Composable
fun DetailScreenContent(
    viewModel: DetailViewModel,
    coroutineDispatcher: DispatcherProvider,
    appState: AppState,
) {
    val uiState by viewModel.viewState.collectAsStateWithLifecycle(
        initialValue = DetailState.Loading,
        context = coroutineDispatcher.main,
    )

    when (uiState) {
        is DetailState.Loading -> {
            SearchPlateAnimation()
        }

        is DetailState.SuccessConsult -> {
            val successConsultState = uiState as DetailState.SuccessConsult
            SearchPlateAnimation()
        }

        is DetailState.SuccessDetail -> {
            val successDetailState = uiState as DetailState.SuccessDetail
            TopBarWithLogo(successDetailState.detail) { appState.onBackClick() }
            HorizontalDivider(Modifier.padding(vertical = 4.dp, horizontal = 16.dp))
            VehicleDetailsScreen(successDetailState.detail, appState)
        }

        is DetailState.Error -> {
            val errorState = uiState as DetailState.Error
            TopBarWithLogo(null) { appState.onBackClick() }
            ErrorScreen(errorState.error)
        }
    }
}

@Composable
fun VehicleDetailsScreen(detail: Detail, appState: AppState) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 4.dp, horizontal = 16.dp),
    ) {
        VehicleBasicInfoSection(detail)
        VehicleHistorySection(detail) { appState.navToReport() }
        VehicleTechnicalDetailsSection(detail)
        VehicleIdentificationSection(detail)
        VehicleAdditionalDetailsSection(detail)
    }
}

@Composable
fun TopBarWithLogo(
    detail: Detail?,
    onBackClicked: () -> Unit,
) {
    val context = LocalContext.current // Get local context to access resources

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BackUiComposable {
            onBackClicked()
        }
        Spacer(modifier = Modifier.width(16.dp))
        detail?.result?.let {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = "${it.repuve?.marca} ${it.repuve?.modelo}",
                    style = MaterialTheme.typography.headlineSmall,
                )
                it.repuve?.let {
                    Text(
                        context.getString(R.string.first_registration, it.fechaRegistro , it.horaRegistro),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).error(R.drawable.car_placeholder)
                    .data(null).crossfade(true).build(),
                placeholder = painterResource(R.drawable.car_placeholder),
                contentDescription = "Logo de la marque",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(72.dp),
            )
        }
    }
}

@Composable
fun VehicleBasicInfoSection(detail: Detail) {
    val context = LocalContext.current // Get local context to access resources
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp), // Bords arrondis pour la Card
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp), // Légère augmentation de l'élévation pour plus de profondeur
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TitleSection(
                title = context.getString(R.string.basic_information),
                icon = R.drawable.ic_infos_base,
            )
            Spacer(modifier = Modifier.height(16.dp)) // Espace après le titre

            DetailItem(
                context.getString(R.string.registration),
                detail.result?.ocra?.vehiculo?.placa?.uppercase(Locale.getDefault()),
            )
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.brand), detail.result?.ocra?.vehiculo?.marca)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.model), detail.result?.repuve?.modelo)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(
                context.getString(R.string.model_year),
                detail.result?.repuve?.anioModelo,
            )
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.origin), detail.result?.ocra?.vehiculo?.origen)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.color), detail.result?.ocra?.vehiculo?.color)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.type), detail.result?.repuve?.tipo)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.NRPV), detail.result?.repuve?.nrpv)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.number_of_doors), detail.result?.repuve?.numPuertas)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.type_of_transport), detail.result?.ocra?.vehiculo?.tipoDeTransporte)
        }
    }
}


@Composable
fun TimelineItem(title: String?, description: String?, iconId: Int) {
    val context = LocalContext.current // Get local context to access resources
    Row(verticalAlignment = Alignment.Top, modifier = Modifier.padding(bottom = 16.dp)) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null, // Description pour l'accessibilité
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = title ?: context.getString(R.string.not_available),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = description ?: context.getString(R.string.not_available),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}


@Composable
fun VehicleHistorySection(detail: Detail, onDemande: () -> Unit) {
    val context = LocalContext.current // Get local context to access resources

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Définit l'élévation de la Card
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TitleSection(
                title = context.getString(R.string.history_information),
                icon = R.drawable.ic_history,
            )
            Spacer(modifier = Modifier.height(16.dp)) // Espace après le titre

            // Élément de la chronologie
            TimelineItem(
                title = "${detail.result?.repuve?.fechaRegistro} | ${detail.result?.repuve?.horaRegistro}" ,
                description = context.getString(R.string.first_registration),
                iconId = R.drawable.ic_key_car
            )

            // Bouton centré
            Button(
                onClick = {
                    FirebaseUtils.logDemandVehicleHistory(plateNumber = detail.placas ?: "UNKNOWN")
                    onDemande()
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(), // Centrer le bouton horizontalement
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            ) {
                Spacer(Modifier.width(8.dp))
                Text(context.getString(R.string.demand_hitory), color = Color.White)
            }
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
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Définit l'élévation de la Card
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TitleSection(
                title = context.getString(R.string.technical_information),
                icon = R.drawable.ic_info_tech,
            )
            Spacer(modifier = Modifier.height(16.dp)) // Espace après le titre
            DetailItem(context.getString(R.string.motor), detail.result?.ocra?.vehiculo?.motor)

            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.cylinders), detail.result?.repuve?.cilindrada)

            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.num_cilindres), detail.result?.repuve?.numCilindros)

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
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Définit l'élévation de la Card
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TitleSection(
                title = context.getString(R.string.vehicle_identification),
                icon = R.drawable.ic_car_id,
            )
            Spacer(modifier = Modifier.height(16.dp)) // Espace après le titre

            DetailItem(context.getString(R.string.vin), detail.result?.pgj?.vin)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            DetailItem(context.getString(R.string.serial_no), detail.result?.ocra?.vehiculo?.numeroSerie)
            HorizontalDivider(Modifier.padding(vertical = 4.dp))

            DetailItem(context.getString(R.string.origin), detail.result?.repuve?.paisOrigen)

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
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Définit l'élévation de la Card
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TitleSection(
                title = context.getString(R.string.additional_information),
                icon = R.drawable.ic_car_plus,
            )
            Spacer(modifier = Modifier.height(16.dp)) // Espace après le titre
            DetailItem(context.getString(R.string.carfax_check_result), detail.result?.carfax?.data?.message)
        }
    }
}

@Composable
fun TitleSection(
    title: String,
    @DrawableRes icon: Int,
) {
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
fun DetailItem(
    label: String,
    value: String?,
    iconId: Int? = null,
) {
    val context = LocalContext.current // Get local context to access resources
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp),
    ) {
        iconId?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = label,
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified,
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = value ?: context.getString(R.string.not_available),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Composable
fun SearchPlateAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("search_anim.json"))

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
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
                modifier = Modifier.size(240.dp), // Ajustez la taille selon vos besoins
            )
            Text(
                text = errorMessage,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 16.dp),
            )
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    AutoScanAppTheme {
//        VehicleDetailsScreen(
//            detail = detail,
//            AppState(navController = NavController(LocalContext.current))
//        )
//    }
//}

