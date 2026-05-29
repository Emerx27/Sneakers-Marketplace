package com.example.sneakers_admin_app.features.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import com.example.sneakers_admin_app.core.navigation.Routes
import com.example.sneakers_admin_app.shared.components.PrimaryButton
import com.example.sneakers_admin_app.ui.theme.AppColors

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    navController: NavController
) {
    LaunchedEffect(viewModel.isSuccess) {
        if(viewModel.isSuccess) {
            navController.navigate(Routes.SNEAKERS) {
                popUpTo(0)

                launchSingleTop = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Log in", fontSize = 22.sp, fontWeight = FontWeight.Bold)

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.email,
                onValueChange = { viewModel.onEmailChange(it) },
                placeholder = { Text("Email", color = AppColors.TextMuted) },
                isError = viewModel.emailError != null,
                supportingText = viewModel.emailError?.let { error ->
                    {
                        Text(error, color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.password,
                onValueChange = viewModel::onPasswordChange,
                placeholder = { Text("Password", color = AppColors.TextMuted) },
                isError = viewModel.passwordError != null,
                supportingText = viewModel.passwordError?.let { error ->
                    {
                        Text(error, color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            PrimaryButton(modifier = Modifier.fillMaxWidth(), text = "Log in", onClick = {viewModel.login()})

            viewModel.generalError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Text(modifier = Modifier.clickable {
                navController.navigate(Routes.REGISTER) {
                    popUpTo(Routes.REGISTER) {
                        inclusive = false
                    }

                    launchSingleTop = true
                } }, text = "¿Don't have an account? Register", fontSize = 12.sp)
        }
    }
}