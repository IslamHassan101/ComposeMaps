package com.example.composemaps

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.composemaps.ui.theme.ComposeMapsTheme
import com.example.composemaps.ui.theme.greenColor
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMapsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                backgroundColor = greenColor,
                                title = {

                                    Text(
                                        text = "GFG",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        color = Color.White
                                    )
                                },
                            )
                        }) {

                        mapUI(context = LocalContext.current)
                    }
                }
            }
        }
    }
}


@Composable
fun mapUI(context: Context) {

    val mapView = rememberMapViewWithLifeCycle()

    val locationName = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 3.dp), horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = locationName.value,
                onValueChange = { locationName.value = it },
                placeholder = { Text(text = "Enter Location") },
                modifier = Modifier
                    .padding(3.dp)
                    .width(300.dp)
                    .height(60.dp),
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(5.dp))
            Button(onClick = {
                getMapLocation(
                    location = locationName.value,
                    context = context,
                    mapView = mapView
                )
            }, modifier = Modifier.padding(3.dp)) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search_icon")
            }
        }
        AndroidView({ mapView }) { mapView ->
            CoroutineScope(Dispatchers.Main).launch {
                val map = mapView.awaitMap()
                map.uiSettings.isZoomControlsEnabled = true
            }
        }

    }

}

private fun getMapLocation(location: String, context: Context, mapView: MapView) {

    var addressList: List<Address>? = null
    mapView.getMapAsync {
        if (location != null || location == "") {
            val geocoder = Geocoder(context)
            try {
                addressList = geocoder.getFromLocationName(location, 1)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val address: Address = addressList!![0]
            val latLng = LatLng(address.latitude, address.longitude)
            it.addMarker(MarkerOptions().position(latLng).title("Marker in$location"))
            it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8f))
        }
    }
}

