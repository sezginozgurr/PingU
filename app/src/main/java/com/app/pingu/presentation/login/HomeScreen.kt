package com.app.pingu.presentation.login

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.core.content.ContextCompat
import com.google.maps.android.compose.Marker
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Canvas as AndroidCanvas
import android.graphics.drawable.BitmapDrawable
import androidx.annotation.DrawableRes
import com.app.pingu.R
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun HomeScreenRoute(
    navigateToVerificationOtp: () -> Unit = {},
) {
    HomeScreen()
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    var userLocation by remember { mutableStateOf<LatLng?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var hasLocationPermission by remember { mutableStateOf(false) }

    val locationPermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    val cameraPositionState = rememberCameraPositionState()

    // Mock kullanıcılar
    data class MockUser(val name: String, val latLng: LatLng, val avatarRes: Int)// val avatarRes: Int)
    val mockAvatars = listOf(
        R.drawable.person_1,
        R.drawable.person_2,
        R.drawable.person_3,
        R.drawable.person_4,
        R.drawable.person_5,
        R.drawable.person_6
    )
    val mockUsers = remember(userLocation) {
        if (userLocation == null) emptyList() else {
            val baseLat = userLocation!!.latitude
            val baseLng = userLocation!!.longitude
            List(6) { i ->
                val angle = Math.toRadians((360.0 / 6) * i)
                val radius = 0.03 // yaklaşık 30km
                val lat = baseLat + radius * Math.cos(angle)
                val lng = baseLng + radius * Math.sin(angle)
                MockUser(
                    name = "Person_${i+1}",
                    latLng = LatLng(lat, lng),
                    avatarRes = mockAvatars[i % mockAvatars.size]
                )
            }
        }
    }

    LaunchedEffect(locationPermissionState.status.isGranted) {
        if (locationPermissionState.status.isGranted) {
            hasLocationPermission = true
            try {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location ->
                        if (location != null) {
                            userLocation = LatLng(location.latitude, location.longitude)
                        } else {
                            userLocation = LatLng(41.0082, 28.9784) // İstanbul
                        }
                        isLoading = false
                    }
                    .addOnFailureListener { exception ->
                        userLocation = LatLng(41.0082, 28.9784) // İstanbul
                        isLoading = false
                    }
            } catch (e: SecurityException) {
                userLocation = LatLng(41.0082, 28.9784) // İstanbul
                isLoading = false
            }
        } else {
            // Konum izni yoksa iste
            locationPermissionState.launchPermissionRequest()
            isLoading = false
        }
    }

    // Konum alındığında kamerayı o noktaya taşı
    LaunchedEffect(userLocation) {
        if (userLocation != null && hasLocationPermission) {
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(userLocation!!, 15f),
                durationMs = 1000
            )
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Konum alınıyor...")
                }
            }

            !hasLocationPermission -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Konum izni gerekli")
                }
            }

            else -> {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    properties = MapProperties(
                        isMyLocationEnabled = hasLocationPermission,
                        mapType = MapType.NORMAL
                    )
                ) {
                    // Mock kullanıcı markerları
                    mockUsers.forEach { user ->
                        Log.d("MockUser", "${user.name} - ${user.latLng}")
                        Log.d("UserLocation", userLocation.toString())
                        Marker(
                            state = rememberMarkerState(position = user.latLng),
                            title = "Rider",
                            snippet = "Rider",
                            icon = getBitmapDescriptorFromDrawable(
                                context = context,
                                drawableResId = R.drawable.ic_delivery_1
                            )
                        )
                        /* Marker(
                            state = com.google.maps.android.compose.rememberMarkerState(position = user.latLng),
                            title = user.name,
                            //icon = getBitmapDescriptorFromDrawable(context, user.avatarRes)
                        ) */
                    }
                }
            }
        }
    }
}

fun bitmapDescriptorFromVector(context: Context, @DrawableRes vectorResId: Int): BitmapDescriptor {
    val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
    val bitmap = Bitmap.createBitmap(
        vectorDrawable!!.intrinsicWidth,
        vectorDrawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}

fun getBitmapDescriptorFromDrawable(context: android.content.Context, drawableResId: Int): BitmapDescriptor {
    val drawable = ContextCompat.getDrawable(context, drawableResId)
    if (drawable is BitmapDrawable) {
        return BitmapDescriptorFactory.fromBitmap(drawable.bitmap)
    }
    val width = drawable?.intrinsicWidth ?: 96
    val height = drawable?.intrinsicHeight ?: 96
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = AndroidCanvas(bitmap)
    drawable?.setBounds(0, 0, width, height)
    drawable?.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
