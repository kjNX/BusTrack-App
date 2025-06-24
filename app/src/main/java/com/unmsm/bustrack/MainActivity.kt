package com.unmsm.bustrack

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.rememberIconImage
import com.unmsm.bustrack.ui.theme.BusTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { BusTrackTheme { AppScreen() } }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(title = { Text(text = stringResource(R.string.app_name)) })
}

@Composable
fun MapDisplay(modifier: Modifier = Modifier) {
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
        PointAnnotation(point = Point.fromJson("{\"type\":\"Point\",\"coordinates\":[-77.085826,-12.053679]}")) {
            iconImage = marker
            textField = text
            interactionsState.onClicked {
                Toast.makeText(context, "Tap!", Toast.LENGTH_SHORT).show()
                true
            }
        }
    }
}

@Composable
fun AppScreen() {
    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.MyLocation,
                    contentDescription = "Center location"
                )
            }
        }
    ) { innerPadding ->
        MapDisplay(modifier = Modifier.padding(innerPadding))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() { BusTrackTheme { AppScreen() } }
