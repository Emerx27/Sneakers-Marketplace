package com.example.sneakers_admin_app.features.sneakers.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.sneakers_admin_app.core.navigation.Routes
import com.example.sneakers_admin_app.features.sneakers.presentation.viewmodel.SneakersViewModel
import com.example.sneakers_admin_app.shared.components.ErrorModalScreen
import com.example.sneakers_admin_app.ui.theme.AppColors

@Composable
fun SneakersScreen(
    navController: NavController,
    viewModel: SneakersViewModel = viewModel()
) {

    val sneakers by viewModel.sneakerPreviewList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllSneakers()
    }

    viewModel.errorMessage?.let {
        ErrorModalScreen(message = it, actionText = "Retry", onDismiss = {
            viewModel.getAllSneakers()
        })
        return
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
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Marketplace",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                )

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                navController.navigate(Routes.PROFILE) {
                                launchSingleTop = true
                            } },
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primary
                    ) {
                        Box(
                            Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${viewModel.user?.firstName?.firstOrNull() ?: ""}" +
                                        "${viewModel.user?.lastName?.firstOrNull() ?: ""}",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            }

            LazyVerticalGrid(
                modifier = Modifier.weight(1f),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (sneakers.isEmpty()) {
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "No products listed",
                            textAlign = TextAlign.Center
                        )
                    }
                }

                items(
                    items = sneakers,
                    key = { it.id }) { sneaker ->
                    Card(modifier = Modifier.clickable {
                        navController.navigate(
                            "${Routes.SNEAKER_DETAIL}/${sneaker.id}"
                        ) {
                            launchSingleTop = true
                        }
                    },
                        colors = CardDefaults.cardColors(Color.Transparent),
                        shape = RectangleShape
                    ) {
                        Column {
                            AsyncImage(
                                model = sneaker.thumbnail,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                            )
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text(
                                    text = "$${sneaker.price}",
                                    fontWeight = FontWeight.Bold
                                )

                                Text(text = sneaker.model,
                                    fontWeight = FontWeight.Light,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis)
                            }

                            Text(
                                text = "SKU: ${sneaker.sku}",
                                color = AppColors.TextMuted,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .padding(18.dp)
                .align(Alignment.BottomEnd),
            onClick = {
                navController.navigate(Routes.PUBLISH) {
                    launchSingleTop = true
                }
            },
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary
        ) {

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Create sneaker"
            )
        }
    }
}