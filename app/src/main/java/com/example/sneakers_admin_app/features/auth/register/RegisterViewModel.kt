package com.example.sneakers_admin_app.features.auth.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakers_admin_app.core.network.RetrofitProvider
import com.example.sneakers_admin_app.core.models.errors.MessageErrorResponse
import com.example.sneakers_admin_app.features.auth.models.register.RegisterRequest
import com.example.sneakers_admin_app.core.models.errors.ValidationErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    var firstName by mutableStateOf("")
        private set
    var lastName by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    var isSuccess by mutableStateOf(false)
        private set

    var firstNameError by mutableStateOf<String?>(null)
        private set

    var lastNameError by mutableStateOf<String?>(null)
        private set

    var emailError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    var generalError by mutableStateOf<String?>(null)
        private set

    fun onFirstNameChange(value: String) {
        firstName = value
    }

    fun onLastNameChange(value: String) {
        lastName = value
    }

    fun onEmailChange(value: String) {
        email = value
    }

    fun onPasswordChange(value: String) {
        password = value
    }

    fun clearErrors() {
        firstNameError = null
        lastNameError = null
        emailError = null
        passwordError = null
        generalError = null
    }

    fun resetSuccess() {
        isSuccess = false
    }

    fun resetFields() {
        firstName = ""
        lastName = ""
        email = ""
        password = ""
    }

    fun register() {

        viewModelScope.launch {

            try {

                clearErrors()

                val response = RetrofitProvider
                    .authApi
                    .register(
                        RegisterRequest(
                            firstName = firstName,
                            lastName = lastName,
                            email = email,
                            password = password
                        )
                    )

                if(response.isSuccessful) {
                    isSuccess = true
                    resetFields()
                } else {

                    val errorJson =
                        response.errorBody()?.string()

                    try {

                        val validationErrors =
                            Gson().fromJson(
                                errorJson,
                                ValidationErrorResponse::class.java
                            )

                        firstNameError =
                            validationErrors.error["firstName"]

                        lastNameError =
                            validationErrors.error["lastName"]

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

            } catch(_: Exception) {
                generalError =
                    "No internet connection"
            }
        }
    }
}