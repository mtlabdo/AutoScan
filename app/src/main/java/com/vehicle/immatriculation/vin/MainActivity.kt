package com.vehicle.immatriculation.vin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vehicle.immatriculation.vin.dispatcher.DispatcherProvider
import com.vehicle.immatriculation.vin.navigation.AppNavigation
import com.vehicle.immatriculation.vin.ui.theme.AutoScanAppTheme
import com.vehicle.immatriculation.vin.view.viewmodel.DetailViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipesAppTheme()
        }
    }

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun detailViewModelFactory(): DetailViewModel.Factory
    }

    @Inject
    lateinit var coroutineDispatcher: DispatcherProvider

    @Composable
    fun RecipesAppTheme() {
        AutoScanAppTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier =
                    Modifier
                        .fillMaxSize(),
            ) {
                Box {
                    AppNavigation(coroutineDispatcher)
                }
            }
        }
    }
}
