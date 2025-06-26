package com.unmsm.bustrack.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unmsm.bustrack.data.model.NextBus
import com.unmsm.bustrack.ui.theme.BusTrackTheme

@Composable
fun RouteItem(nextBus: NextBus, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.clickable(onClick = {}).padding(8.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = nextBus.name, style = MaterialTheme.typography.headlineMedium)
        Text(text = nextBus.formattedTime)
    }
}

@Composable
fun InformationDisplay(predictions: List<NextBus>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) { items(predictions) { i -> RouteItem(i) } }
}

@Composable
fun InformationStage(
    informationUiState: InformationUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    when(informationUiState) {
        is InformationUiState.Error -> ErrorScreen(onRetry, modifier)
        is InformationUiState.Loading -> LoadingScreen(modifier)
        is InformationUiState.Success -> InformationDisplay(informationUiState.map, modifier)
    }
}

@Composable
fun InformationScreen(
    onReturn: () -> Unit,
    viewModel: InformationViewModel = viewModel(factory = InformationViewModel.Factory)
) {
    BusTrackTheme {
        Scaffold(topBar = { TopBar(onReturn, viewModel::getPredictions) }) { innerPadding ->
            InformationStage(
                informationUiState = viewModel.informationUiState,
                onRetry = viewModel::getPredictions,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onReturn: () -> Unit, onRefresh: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Información") },
        navigationIcon = {
            IconButton(onClick = onReturn) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Regresar"
                )
            }
        },
        actions = {
            IconButton(onClick = onRefresh) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Recargar"
                )
            }
        }
    )
}