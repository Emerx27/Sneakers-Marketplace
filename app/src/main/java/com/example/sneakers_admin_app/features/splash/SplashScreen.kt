package com.example.sneakers_admin_app.features.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sneakers_admin_app.core.navigation.Routes

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = viewModel()
) {

    val hasToken =
        viewModel.hasToken.value

    LaunchedEffect(hasToken) {

        when (hasToken) {
            true -> {
                navController.navigate(Routes.SNEAKERS) {
                    popUpTo("splash") {
                        inclusive = true
                    }
                }
            }

            false -> {
                navController.navigate(Routes.WELCOME) {
                    popUpTo("splash") {
                        inclusive = true
                    }
                }
            }

            null -> {}
        }
    }
}