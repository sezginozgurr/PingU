package com.app.pingu.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Destination {

    @Serializable
    data object Empty : Destination()

    @Serializable
    data object Onboarding : Destination()

    @Serializable
    data object Login : Destination()

}