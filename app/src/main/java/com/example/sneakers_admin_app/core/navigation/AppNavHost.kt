package com.example.sneakers_admin_app.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sneakers_admin_app.core.navigation.graphs.authGraph
import com.example.sneakers_admin_app.core.navigation.graphs.sneakersGraph
import com.example.sneakers_admin_app.features.splash.SplashScreen

@Composable
fun AppNavHost(paddingValues: PaddingValues) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        modifier = Modifier.padding(paddingValues)
    ) {

        composable(Routes.SPLASH) {
            SplashScreen(navController)
        }

        authGraph(navController)

        sneakersGraph(navController)
    }
}