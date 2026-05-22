package com.example.sneakers_admin_app.features.auth.login

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakers_admin_app.core.network.RetrofitProvider
import com.example.sneakers_admin_app.features.auth.models.login.LoginRequest
import com.example.sneakers_admin_app.core.models.errors.MessageErrorResponse
import com.example.sneakers_admin_app.core.models.errors.ValidationErrorResponse
import com.example.sneakers_admin_app.core.storage.UserPreferences
import com.google.gson.Gson
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val tokenManager =
        UserPreferences(application)

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    var emailError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    var generalError by mutableStateOf<String?>(null)
        private set

    var isSuccess by mutableStateOf(false)
        private set

    fun onEmailChange(value: String) {
        email = value
    }

    fun onPasswordChange(value: String) {
        password = value
    }

    private fun clearErrors() {
        emailError = null
        passwordError = null
        generalError = null
    }

    fun login() {
        viewModelScope.launch {
            try {
                clearErrors()

                val response = RetrofitProvider
                        .authApi
                        .login(
                            LoginRequest(
                                email = email,
                                password = password
                            )
                        )

                if(response.isSuccessful) {
                    val body = response.body()

                    if(body != null) {

                        tokenManager.saveToken(body.token)

                        isSuccess = true
                    }
                } else {
                    val errorJson =
                        response.errorBody()?.string()

                    try {

                        val validationErrors =
                            Gson().fromJson(
                                errorJson,
                                ValidationErrorResponse::class.java
                            )
                        emailError =
                            validationErrors.error["email"]

                        passwordError =
                            validationErrors.error["password"]

                    } catch(_: Exception) {

                        val messageError =
                            Gson().fromJson(
                                errorJson,
                                MessageErrorResponse::class.java
                            )

                        generalError =
                            messageError.error
                    }
                }
            } catch (_: Exception) {
                generalError =
                    "No internet connection"
            }
        }
    }
}