package com.example.sneakers_admin_app.features.onboarding.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sneakers_admin_app.navigation.Routes
import com.example.sneakers_admin_app.shared.components.PrimaryButton

@Composable
fun WelcomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().padding(horizontal = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceAround) {
            Column {
                Text("Join Hypestock", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Text("The largest sneaker market")
            }

            PrimaryButton(modifier = Modifier.fillMaxWidth(), text = "Get started", onClick = {navController.navigate(Routes.REGISTER) {launchSingleTop = true} })
        }
    }
}