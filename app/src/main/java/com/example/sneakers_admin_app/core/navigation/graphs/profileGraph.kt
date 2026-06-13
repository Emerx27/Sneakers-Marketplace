package com.example.sneakers_admin_app.core.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.sneakers_admin_app.core.navigation.Routes
import com.example.sneakers_admin_app.features.profile.presentation.screens.ProfileScreen

fun NavGraphBuilder.profileGraph(
    navController: NavController
) {
    composable(Routes.PROFILE) {
        ProfileScreen(
            navController = navController
        )
    }
}