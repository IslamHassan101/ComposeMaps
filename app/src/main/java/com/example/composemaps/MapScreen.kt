package com.example.composemaps

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import java.io.IOException


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MapScreen() {

    var mMap: GoogleMap? = null

    val positions = listOf(
        Positions("egypt", 30.03, 31.0),
        Positions("egypt", 30.4, 31.5)
    )

    val cameraPositionState = rememberCameraPositionState {
        positions.forEach {
            position = CameraPosition.fromLatLngZoom(LatLng(it.lat, it.long), 10f)
        }
    }
    Scaffold(topBar = {
        TextField(value = "", onValueChange = {})
    }) {
        GoogleMap(cameraPositionState = cameraPositionState, onMyLocationButtonClick = { true }) {

            positions.forEach { position ->
                MarkerInfoWindow(
                    state = MarkerState(LatLng(position.lat, position.long)),
                ) {
                    Text(
                        text = position.title,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
        }
    }
}