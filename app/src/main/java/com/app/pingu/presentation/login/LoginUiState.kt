package com.app.pingu.presentation.login

data class LoginUiState(
    val isLoginSuccess: Boolean = false,
    val isLoginError: Boolean = false,
    val isButtonEnabled: Boolean =false
)