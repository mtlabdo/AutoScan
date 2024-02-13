package com.vehicule.immatriculation.histo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vehicule.immatriculation.histo.dispatcher.DispatcherProvider
import com.vehicule.immatriculation.histo.navigation.AppNavigation
import com.vehicule.immatriculation.histo.ui.theme.AndroidTestTheme
import com.vehicule.immatriculation.histo.view.viewmodel.DetailViewModel
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
        AndroidTestTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box() {
                    AppNavigation(coroutineDispatcher)
                }
            }
        }
    }
}