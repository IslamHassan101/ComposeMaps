package com.example.composemaps

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import java.io.IOException


//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun MapScreen() {
//
//    var addressList: List<Address>? = null
//    val locationName = remember { mutableStateOf("") }
//
//    val context = LocalContext.current
//    if (locationName.value != null || locationName.value == "") {
//        val geocoder = Geocoder(context)
//        try {
//            addressList = geocoder.getFromLocationName(locationName.value, 1)
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//
//        val address: Address = addressList!![0]
//        val latLng = LatLng(address.latitude, address.longitude)
//        val cameraPositionState = rememberCameraPositionState {
//            position = CameraPosition.fromLatLngZoom(latLng, 8f)
//
//        }
//        Scaffold(topBar = {
//            TextField(
//                value = locationName.value,
//                onValueChange = { locationName.value = it },
//                placeholder = { Text(text = "Enter Location") },
//                modifier = Modifier
//                    .padding(3.dp)
//                    .width(300.dp)
//                    .height(60.dp),
//                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
//                singleLine = true
//            )
//            Button(onClick = {
//                locationName.value
//            }, modifier = Modifier.padding(3.dp)) {
//                Icon(imageVector = Icons.Default.Search, contentDescription = "search_icon")
//            }
//        }) {
//            GoogleMap(
//                cameraPositionState = cameraPositionState
//            ) {
//
//                MarkerInfoWindow(
//                    state = MarkerState(latLng),
//                ) {
//                    Text(
//                        text = locationName.value,
//                        fontWeight = FontWeight.Bold,
//                        style = MaterialTheme.typography.h4,
//                        color = MaterialTheme.colors.primary
//                    )
//                }
//            }
//        }
//    }
//}
//
