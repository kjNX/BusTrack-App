package com.unmsm.bustrack.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.rememberIconImage
import com.unmsm.bustrack.R
import com.unmsm.bustrack.data.model.MapData

@Composable
fun MapDisplay(mapData: MapData, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    MapboxMap(
        modifier = modifier.fillMaxSize(),
        mapViewportState = rememberMapViewportState {
            setCameraOptions {
                zoom(16.0)
//                center(Point.fromLngLat(-98.0, 39.5))
                center(Point.fromLngLat(-77.085826, -12.053679))
                pitch(0.0)
                bearing(0.0)
            }
        }
    ) {
        var text by remember { mutableStateOf("Paradero") }

        var markerResourceId by remember { mutableIntStateOf(R.drawable.bus_marker) }
        val marker = rememberIconImage(key = markerResourceId, painter = painterResource(markerResourceId))
        mapData.busStops.forEach { i ->
            PointAnnotation(point = Point.fromLngLat(i.longitude, i.latitude)) {
                iconImage = marker
                textField = i.name
                interactionsState.onClicked {
                    Toast.makeText(context, "Hola!", Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }
    }
}

@Composable
fun ErrorScreen(onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Column {

    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column {

    }
}

@Composable
fun MapScreen(mapsUiState: MapUiState, onRetry: () -> Unit, modifier: Modifier = Modifier) {
    when(mapsUiState) {
        is MapUiState.Error -> ErrorScreen(onRetry, modifier)
        is MapUiState.Loading -> LoadingScreen(modifier)
        is MapUiState.Success -> MapDisplay(mapsUiState.map, modifier)
    }
}