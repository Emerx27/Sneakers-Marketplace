package com.example.sneakers_admin_app.features.auth.confirm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.example.sneakers_admin_app.core.navigation.Routes
import com.example.sneakers_admin_app.shared.components.PrimaryButton

@Composable
fun ConfirmScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text("Account created successfully", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("Now you can log in", fontSize = 14.sp)
            PrimaryButton(modifier = Modifier.fillMaxWidth(), text = "Log in", onClick = {
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.CONFIRM) {
                        inclusive = true
                    }
                }
            })
        }
    }
}