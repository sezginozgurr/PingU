package com.app.pingu.presentation.login

import kotlinx.serialization.Serializable

sealed interface HomeUiEvent {

    @Serializable
    data object NavigateVerificationOtp : HomeUiEvent
}
