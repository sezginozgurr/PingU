package com.app.pingu.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.pingu.R
import com.app.pingu.core.components.SecondaryButton
import com.app.pingu.ui.theme.black
import com.app.pingu.ui.theme.regularLightGray15
import com.app.pingu.ui.theme.regularWhite24
import com.app.pingu.utils.extension.buildBoldAnnotatedString

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    navigateToLoginScreen: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val description = stringResource(R.string.onboarding_description)

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "SiTaxi Logo",
                modifier = Modifier
                    .height(80.dp)
                    .padding(bottom = 32.dp)
            )
            Text(
                text = stringResource(R.string.onboarding_title),
                style = regularWhite24,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = description,
                style = regularLightGray15,
                textAlign = TextAlign.Center
            )
            SecondaryButton(
                text = stringResource(R.string.onboarding_button),
                modifier = Modifier.padding(top = 24.dp),
                actionClickListener = {
                    viewModel.onGetStartedClick()
                    navigateToLoginScreen()
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(navigateToLoginScreen = {})
}