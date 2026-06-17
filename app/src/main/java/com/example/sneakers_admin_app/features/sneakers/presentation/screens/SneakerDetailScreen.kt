package com.example.sneakers_admin_app.features.sneakers.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sneakers_admin_app.features.sneakers.presentation.viewmodel.SneakerDetailViewModel
import com.example.sneakers_admin_app.shared.components.layout.ScreenHeader

@Composable
fun SneakerDetailScreen(
    navController: NavController,
    sneakerId: Long?,
    viewModel: SneakerDetailViewModel = viewModel()
) {
    LaunchedEffect(sneakerId) {
        sneakerId?.let {
            viewModel.getSneakerById(it)
        }
    }

    if (viewModel.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                trackColor = MaterialTheme.colorScheme.background,
                color = MaterialTheme.colorScheme.primary
            )
        }
        return
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            ScreenHeader(
                title = "Sneaker details",
                onBackClick = { navController.popBackStack() }
            )

            viewModel.errorMessage?.let {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(it)
                }

                return
            }

            val sneaker = viewModel.sneaker ?: return

            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Column {
                    Text(
                        text = "${sneaker.brand} ${sneaker.model}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "$%.2f".format(sneaker.price)
                    )
                }

                Column {
                    Text(
                        "Product description",
                        fontWeight = FontWeight.Bold
                    )

                    Text(sneaker.description ?: "No description provided")
                }

                Column {
                    Text(
                        "Seller",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "${sneaker.firstName} ${sneaker.lastName}"
                    )
                }
            }
        }
    }
}