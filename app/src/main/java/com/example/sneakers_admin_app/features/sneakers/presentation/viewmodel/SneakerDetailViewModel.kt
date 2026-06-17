package com.example.sneakers_admin_app.features.sneakers.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakers_admin_app.core.models.errors.MessageErrorResponse
import com.example.sneakers_admin_app.core.network.RetrofitProvider
import com.example.sneakers_admin_app.features.sneakers.models.SneakerDetailResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch

class SneakerDetailViewModel : ViewModel() {
    var sneaker by mutableStateOf<SneakerDetailResponse?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun getSneakerById(
        sneakerId: Long?
    ) {
        viewModelScope.launch {

            errorMessage = null
            isLoading = true

            try {

                val response =
                    RetrofitProvider
                        .sneakersApi
                        .getSneakerById(sneakerId)

                if (response.isSuccessful) {

                    sneaker = response.body()

                } else {

                    val gson = Gson()

                    val errorResponse =
                        gson.fromJson(
                            response.errorBody()?.string(),
                            MessageErrorResponse::class.java
                        )

                    errorMessage =
                        errorResponse.error
                }

            } catch (_: Exception) {

                errorMessage =
                    "Error connecting to the server"

            } finally {

                isLoading = false
            }
        }
    }

    fun clearError() {
        errorMessage = null
    }
}