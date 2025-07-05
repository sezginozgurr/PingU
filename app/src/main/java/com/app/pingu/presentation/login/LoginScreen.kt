package com.app.pingu.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreenRoute(
    navigateToVerificationOtp: () -> Unit,
) {
    LoginScreen(
        navigateToVerificationOtp = navigateToVerificationOtp
    )
}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToVerificationOtp: () -> Unit
) {
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginScreen(navigateToVerificationOtp = {})
}