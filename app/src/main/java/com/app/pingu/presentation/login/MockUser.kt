package com.app.pingu.presentation.login

import com.google.android.gms.maps.model.LatLng

data class MockUser(val name: String, val latLng: LatLng, val avatarRes: Int,val favorites: List<String>)
