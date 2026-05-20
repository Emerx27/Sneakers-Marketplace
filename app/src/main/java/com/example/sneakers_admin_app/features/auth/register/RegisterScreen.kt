package com.example.sneakers_admin_app.features.auth.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sneakers_admin_app.features.auth.register.RegisterViewModel
import com.example.sneakers_admin_app.navigation.Routes
import com.example.sneakers_admin_app.shared.components.PrimaryButton
import com.example.sneakers_admin_app.ui.theme.AppColors

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(),
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Create an account", fontSize = 22.sp, fontWeight = FontWeight.Bold)

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.firstName,
                onValueChange = {viewModel.onFirstNameChange(it)},
                placeholder = {Text("First name", color = AppColors.TextMuted)}
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.lastName,
                onValueChange = {viewModel.onLastNameChange(it)},
                placeholder = {Text("Last name", color = AppColors.TextMuted)}
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.email,
                onValueChange = {viewModel.onEmailChange(it)},
                placeholder = {Text("Email", color = AppColors.TextMuted)}
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.password,
                    onValueChange = {viewModel.onPasswordChange(it)},
                    placeholder = {Text("Password", color = AppColors.TextMuted)}
                )

                Text(text = "- At least 8 characters", color = AppColors.TextMuted, fontSize = 12.sp)
            }

            PrimaryButton(modifier = Modifier.fillMaxWidth(), text = "Create account", onClick = {navController.navigate(Routes.CONFIRM)})

            Text(modifier = Modifier.clickable {navController.navigate(Routes.LOGIN) {launchSingleTop = true} }, text = "¿Already have an account? Log in", fontSize = 12.sp)
        }
    }
}