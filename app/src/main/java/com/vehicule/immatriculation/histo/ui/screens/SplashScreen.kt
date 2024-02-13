import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vehicule.immatriculation.histo.R
import com.vehicule.immatriculation.histo.navigation.AppState
import com.vehicule.immatriculation.histo.ui.theme.AndroidTestTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(appState: AppState?) {
    // État pour démarrer le décompte
    val isReady = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        delay(2000) // Délai de 2 secondes pour l'écran de démarrage
        isReady.value = true
    }

    // Navigation vers l'écran principal une fois prêt
    LaunchedEffect(key1 = isReady.value) {
        if (isReady.value) {
            appState?.navigateToHome()
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Remplacez 'R.drawable.logo' par l'ID de ressource de votre logo

            LoadingScreen()
        }
    }
}


@Composable
fun LoadingScreen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("loading_splash.json"))

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(
                composition = composition,
                speed = 1.2f,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(240.dp) // Ajustez la taille selon vos besoins
            )

            Text(
                text = LocalContext.current.getString(R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = LocalContext.current.getString(R.string.splash_text),
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 500, heightDp = 1000)
@Composable
fun DefaultPreview() {
    AndroidTestTheme {
        SplashScreen(null)
    }
}

