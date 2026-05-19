package com.example.sneakers_admin_app.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.sneakers_admin_app.features.auth.presentation.screens.ConfirmScreen
import com.example.sneakers_admin_app.features.auth.presentation.screens.LoginScreen
import com.example.sneakers_admin_app.features.auth.presentation.screens.RegisterScreen
import com.example.sneakers_admin_app.features.onboarding.presentation.screens.WelcomeScreen
import com.example.sneakers_admin_app.navigation.Routes

fun NavGraphBuilder.authGraph(
    navController: NavController
) {

    composable(Routes.WELCOME) {

        WelcomeScreen(
            navController = navController
        )
    }

    composable(Routes.REGISTER) {

        RegisterScreen(
            navController = navController
        )
    }

    composable(Routes.LOGIN) {

        LoginScreen(
            navController = navController
        )
    }

    composable(Routes.CONFIRM) {

        ConfirmScreen(
            navController = navController
        )
    }
}