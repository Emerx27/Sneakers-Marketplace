package com.example.sneakers_admin_app.core.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.sneakers_admin_app.features.auth.confirm.ConfirmScreen
import com.example.sneakers_admin_app.features.auth.login.LoginScreen
import com.example.sneakers_admin_app.features.auth.register.RegisterScreen
import com.example.sneakers_admin_app.features.onboarding.presentation.screens.WelcomeScreen
import com.example.sneakers_admin_app.core.navigation.Routes

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