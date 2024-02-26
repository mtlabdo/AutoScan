import android.view.ViewTreeObserver

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

import com.vehicle.immatriculation.vin.dispatcher.DispatcherProvider
import com.vehicle.immatriculation.vin.model.History
import com.vehicle.immatriculation.vin.R
import com.vehicle.immatriculation.vin.navigation.AppState
import com.vehicle.immatriculation.vin.ui.widget.LoadingIndicator
import com.vehicle.immatriculation.vin.ui.widget.PlateWidget
import com.vehicle.immatriculation.vin.ui.widget.TopBar
import com.vehicle.immatriculation.vin.utils.Const
import com.vehicle.immatriculation.vin.view.state.HomeState
import com.vehicle.immatriculation.vin.view.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    coroutineDispatcher: DispatcherProvider,
    appState: AppState
) {

    DisposableEffect(Unit) {
        val callback = NavController.OnDestinationChangedListener { _, _, _ ->
            viewModel.getHistory() // Refresh data when navigating back
        }
        appState.addOnDestinationChangedListener(callback)
        onDispose {
            appState.removeOnDestinationChangedListener(callback)
        }
    }

    Surface(color = MaterialTheme.colorScheme.background) {
        Scaffold(
            topBar = { TopBar(appState) },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxHeight()
                ) { HomeScreenContent(viewModel, coroutineDispatcher, appState) }
            }
        )
    }
}


@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel,
    coroutineDispatcher: DispatcherProvider,
    appState: AppState
) {
    var searchText by remember { mutableStateOf("") }
    var isSearchBarFocused by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current // To access string resources

    val view = LocalView.current
    val viewTreeObserver = view.viewTreeObserver
    DisposableEffect(viewTreeObserver) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val isKeyboardOpen = ViewCompat.getRootWindowInsets(view)
                ?.isVisible(WindowInsetsCompat.Type.ime()) ?: true
            isSearchBarFocused = isKeyboardOpen
        }

        viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    val uiState by viewModel.viewState.collectAsStateWithLifecycle(
        initialValue = HomeState.Loading,
        context = coroutineDispatcher.main
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight()
    ) { // Remplir la hauteur disponible

        if (!isSearchBarFocused) {
            QuickInfoWidget()
        }

        Spacer(modifier = Modifier.weight(0.2f)) // Utiliser un Spacer avec un poids pour créer de l'espace
        Spacer(modifier = Modifier.height(30.dp)) // Utiliser un Spacer avec un poids pour créer de l'espace

        SnackbarHost(hostState = snackbarHostState)

        PlateWidget(
            plateText = searchText,
            onFocusChange = { isFocused ->
                isSearchBarFocused = isFocused
            },
            onSearchTextChanged = { newText ->
                searchText = newText
            },
            onSearch = {
                if (searchText.isNotBlank()) {
                    searchText = ""
                }
            })


        Spacer(modifier = Modifier.height(12.dp)) // Espace fixe entre SearchingBar et ActionsSearch
        ActionsSearch(searchText, snackbarHostState) {
            if (searchText.isNotBlank()) {
                appState.navigateToVehicleDetail(searchText)
                viewModel.addHistoryItem(searchText)
            }
        }

        Spacer(modifier = Modifier.weight(1f)) // Utiliser un Spacer avec un poids pour créer de l'espace
        Spacer(modifier = Modifier.height(25.dp)) // Utiliser un Spacer avec un poids pour créer de l'espace

        if (!isSearchBarFocused) {
            when (uiState) {
                is HomeState.Loading -> {
                    LoadingIndicator()
                }

                is HomeState.Success -> {
                    val successState = uiState as HomeState.Success
                    if (successState.historyList.isNotEmpty()) {
                        Text(
                            context.getString(R.string.search_history_title),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W700),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        LazyColumn() {
                            items(count = successState.historyList.size) { index ->
                                val invertedIndex = successState.historyList.size - 1 - index
                                SearchHistoryItem(successState.historyList[invertedIndex]) { plateNumber ->
                                    viewModel.deleteHistoryItem(plateNumber)
                                }
                            }
                        }
                    }
                }

                is HomeState.Error -> {
                    val errorState = uiState as HomeState.Error
                    //ErrorText(errorState.error)
                }

            }
        }
    }
}

@Composable
fun SearchHistoryItem(historyItem: History, onDelete: (Int) -> Unit) {
    val context = LocalContext.current // To access string resources
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Filled.History, contentDescription = context.getString(R.string.search_history_title), modifier = Modifier.padding(horizontal = 8.dp))
        Text(historyItem.plateNumber, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f).padding(horizontal = 8.dp))
        IconButton(onClick = { onDelete(historyItem.id) }) {
            Icon(Icons.Filled.Delete, contentDescription = context.getString(R.string.delete_action))
        }
    }
}
@Composable
fun ActionsSearch(
    searchText: String,
    snackbarHostState: SnackbarHostState,
    onSearch: () -> Unit
) {
    val context = LocalContext.current // To access string resources
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), // Add padding around the row for better spacing
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                if (searchText.isBlank()) {
                    CoroutineScope(Dispatchers.Main).launch {
                        snackbarHostState.showSnackbar(
                            message = context.getString(R.string.search_plate_hint),
                            duration = SnackbarDuration.Short
                        )
                    }
                } else {
                    onSearch()
                }
            },
            // Styling remains the same
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Icon(Icons.Filled.Search, contentDescription = context.getString(R.string.search_action), tint = Color.White)
            Spacer(Modifier.width(8.dp))
            Text(context.getString(R.string.search_action), color = Color.White)
        }
    }
}

@Composable
fun QuickInfoWidget() {
    val context = LocalContext.current // To access string resources
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(context.getString(R.string.daily_tip_title), style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(Const.conseilsDuJour.random(), style = MaterialTheme.typography.bodyMedium)
        }
    }
}
