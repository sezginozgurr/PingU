package com.app.pingu.presentation.login

import kotlinx.serialization.Serializable

sealed interface LoginUiEvent {

    @Serializable
    data object NavigateVerificationOtp : LoginUiEvent
}
