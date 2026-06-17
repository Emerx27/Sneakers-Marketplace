package com.example.sneakers_admin_app.core.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.sneakers_admin_app.core.navigation.Routes
import com.example.sneakers_admin_app.features.sneakers.presentation.screens.PublishSneakerScreen
import com.example.sneakers_admin_app.features.sneakers.presentation.screens.SneakerDetailScreen
import com.example.sneakers_admin_app.features.sneakers.presentation.screens.SneakersScreen

fun NavGraphBuilder.sneakersGraph(
    navController: NavController
) {
    composable(Routes.SNEAKERS) {

        SneakersScreen(
            navController = navController
        )
    }

    composable(Routes.PUBLISH) {
        PublishSneakerScreen(
            navController = navController
        )
    }

    composable(
        route = "${Routes.SNEAKER_DETAIL}/{id}"
    ) { backStackEntry ->

        val id =
            backStackEntry.arguments
                ?.getString("id")
                ?.toLongOrNull()

        SneakerDetailScreen(sneakerId = id, navController = navController)
    }
}