package com.example.sneakers_admin_app.features.profile.presentation.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.sneakers_admin_app.core.navigation.Routes
import com.example.sneakers_admin_app.features.profile.presentation.viewmodel.ProfileViewModel
import com.example.sneakers_admin_app.shared.components.layout.ScreenHeader
import com.example.sneakers_admin_app.ui.theme.AppColors

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel()
) {
    val activeSneakers by viewModel.activeSneakerList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getSneakers()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            ScreenHeader(
                title = "Profile",
                onBackClick = { navController.popBackStack() }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .size(100.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text =
                                "${viewModel.user?.firstName?.firstOrNull() ?: ""}" +
                                        "${viewModel.user?.lastName?.firstOrNull() ?: ""}",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Text(
                    text = "${viewModel.user?.firstName?.substringBefore(" ") ?: ""} " +
                            (viewModel.user?.lastName?.substringBefore(" ") ?: ""),
                    fontWeight = FontWeight.Bold, fontSize = 22.sp
                )
            }


            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {

                Text(
                    "Active posts",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(activeSneakers) { sneaker ->
                        Card(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(
                                        "${Routes.SNEAKER_DETAIL}/${sneaker.id}"
                                    ) {
                                        launchSingleTop = true
                                    }
                                },
                            colors = CardDefaults.cardColors(Color.Transparent),
                            shape = RectangleShape
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp),
                                model = sneaker.thumbnail,
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text(
                                    text = "$${sneaker.price}",
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = sneaker.model,
                                    fontWeight = FontWeight.Light,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}