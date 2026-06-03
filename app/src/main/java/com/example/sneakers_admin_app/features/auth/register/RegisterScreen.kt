package com.example.sneakers_admin_app.features.auth.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sneakers_admin_app.core.navigation.Routes
import com.example.sneakers_admin_app.shared.components.PrimaryButton
import com.example.sneakers_admin_app.shared.components.PrimaryTextField
import com.example.sneakers_admin_app.ui.theme.AppColors

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(),
    navController: NavController
) {

    LaunchedEffect(viewModel.isSuccess) {
        if(viewModel.isSuccess) {

            navController.navigate(Routes.CONFIRM) {
                launchSingleTop = true
            }
        }

        viewModel.resetSuccess()
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.clearErrors()
        }
    }

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

            PrimaryTextField(
                value = viewModel.firstName,
                onValueChange = { viewModel.onFirstNameChange(it) },
                placeholder = "First name",
                isError = viewModel.firstNameError != null,
                errorText = viewModel.firstNameError
            )

            PrimaryTextField(
                value = viewModel.lastName,
                onValueChange = { viewModel.onLastNameChange(it) },
                placeholder = "Last name",
                isError = viewModel.lastNameError != null,
                errorText = viewModel.lastNameError
            )

            PrimaryTextField(
                value = viewModel.email,
                onValueChange = { viewModel.onEmailChange(it) },
                placeholder = "Email",
                isError = viewModel.emailError != null,
                errorText = viewModel.emailError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                PrimaryTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    placeholder = "Password",
                    isError = viewModel.passwordError != null,
                    errorText = viewModel.passwordError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )

                Text(
                    text = "- At least 8 characters",
                    color = AppColors.TextMuted,
                    fontSize = 12.sp
                )
            }

            PrimaryButton(modifier = Modifier.fillMaxWidth(), text = "Create account", onClick = {viewModel.register()})

            viewModel.generalError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Text(modifier = Modifier.clickable {navController.navigate(Routes.LOGIN) {launchSingleTop = true} }, text = "¿Already have an account? Log in", fontSize = 12.sp)
        }
    }
}