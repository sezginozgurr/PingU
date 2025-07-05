package com.app.pingu.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.pingu.presentation.login.LoginScreenRoute
import com.app.pingu.ui.theme.black
import com.app.pingu.presentation.onboarding.OnboardingScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(500)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(500)
            )
        }
    ) {
        composable<Destination.Empty> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(black),
            )
        }
        composable<Destination.Onboarding> {
            OnboardingScreen(
                navigateToLoginScreen = {
                    navController.navigate(Destination.Login) {
                        popUpTo<Destination.Onboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Destination.Login> {
            LoginScreenRoute(
                navigateToVerificationOtp = {
                    navController.navigate(it) {
                        popUpTo<Destination.Login> { inclusive = true }
                    }
                }
            )

        }
    }
}