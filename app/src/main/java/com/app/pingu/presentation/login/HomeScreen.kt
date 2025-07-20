package com.app.pingu.presentation.login

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.Toast
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
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.pingu.R
import com.app.pingu.core.components.UserInfoBottomSheet
import com.app.pingu.core.components.UserInfoDialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlin.math.cos
import kotlin.math.sin
import android.graphics.Canvas as AndroidCanvas

@Composable
fun HomeScreenRoute(
    navigateToVerificationOtp: () -> Unit = {},
) {
    val context = LocalContext.current
    var selectedUser by remember { mutableStateOf<MockUser?>(null) }
    HomeScreen { user ->
        selectedUser = user
    }

    selectedUser?.let { user ->
        UserInfoBottomSheet(
            userName = user.name,
            userAvatar = user.avatarRes,
            userFavorites = user.favorites, // ← EKLENEN FAVORİLER
            onDismiss = {
                selectedUser = null // ← DIALOG’U KAPAT
            },
            onMessageClick = {
                Toast.makeText(context, "${user.name} kişisine mesaj atıldı", Toast.LENGTH_SHORT)
                    .show()
            },
            onVibrateClick = {
                Toast.makeText(context, "${user.name} için titreşim gönderildi", Toast.LENGTH_SHORT)
                    .show()
            }
        )
    }

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickUser: (MockUser) -> Unit
) {
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState()

    val mısırÇarşısıKonumu = LatLng(41.016431, 28.970083)
    val userLocation = remember { mutableStateOf(mısırÇarşısıKonumu) }
    val hasLocationPermission = remember { mutableStateOf(true) } // Doğrudan true, mock için
    val isLoading = remember { mutableStateOf(false) }

    val mockAvatars = listOf(
        R.drawable.ic_user_first,
        R.drawable.ic_user_second,
        R.drawable.ic_user_third,
        R.drawable.ic_user_fourth,
        R.drawable.ic_user_fifth,
        R.drawable.ic_user_fifth
    )

    val mockUsers = remember {
        val baseLat = mısırÇarşısıKonumu.latitude
        val baseLng = mısırÇarşısıKonumu.longitude
        val radius = 0.002 // yaklaşık 1km

        val allFavorites = listOf(
            listOf("Futbol", "Kitap", "Kahve"),
            listOf("Basketbol", "Seyahat", "Film"),
            listOf("Müzik", "Yüzme", "Kitap"),
            listOf("Yemek", "Film", "Koşu"),
            listOf("Oyun", "Spor", "Kamp"),
            listOf("Sinema", "Tiyatro", "Dizi")
        )

        List(6) { i ->
            val angle = Math.toRadians((360.0 / 6) * i)
            val lat = baseLat + radius * cos(angle)
            val lng = baseLng + radius * sin(angle)
            MockUser(
                name = "Person_${i + 1}",
                latLng = LatLng(lat, lng),
                avatarRes = mockAvatars[i % mockAvatars.size],
                favorites = allFavorites[i % allFavorites.size]
            )
        }
    }

    // Kamerayı Mısır Çarşısı'na odakla
    LaunchedEffect(Unit) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(userLocation.value, 15.5f),
            durationMs = 1000
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when {
            isLoading.value -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Konum alınıyor...")
                }
            }

            !hasLocationPermission.value -> {
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
                        isMyLocationEnabled = hasLocationPermission.value,
                        mapType = MapType.NORMAL
                    )
                ) {
                    // Kullanıcı konumu (ortadaki)
                    Marker(
                        state = rememberMarkerState(position = userLocation.value),
                        title = "Ben",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                    )

                    // Diğer kullanıcılar
                    mockUsers.forEach { user ->
                        Marker(
                            state = rememberMarkerState(position = user.latLng),
                            title = user.name,
                            icon = getBitmapDescriptorFromDrawable(
                                context = context,
                                drawableResId = user.avatarRes
                            ),
                            onClick = {
                                onClickUser(user)
                                true
                            }
                        )
                    }
                }
            }
        }
    }
}


fun getBitmapDescriptorFromDrawable(context: Context, drawableResId: Int): BitmapDescriptor {
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
    HomeScreen() {}
}
