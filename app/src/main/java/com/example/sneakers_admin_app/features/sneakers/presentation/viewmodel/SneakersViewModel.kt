package com.example.sneakers_admin_app.features.sneakers.presentation.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakers_admin_app.core.models.User
import com.example.sneakers_admin_app.core.models.errors.MessageErrorResponse
import com.example.sneakers_admin_app.core.network.RetrofitProvider
import com.example.sneakers_admin_app.core.storage.UserPreferences
import com.example.sneakers_admin_app.features.sneakers.models.SneakerPreview
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SneakersViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)

    var user by mutableStateOf<User?>(null)
        private set

    init {
        viewModelScope.launch {
            user =
                userPreferences.getUser()
        }
    }
    private val _sneakersPreviewList = MutableStateFlow<List<SneakerPreview>>(emptyList())

    val sneakerPreviewList = _sneakersPreviewList.asStateFlow()

    var isLoading by mutableStateOf(false)
        private set

    var isEmpty by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun getAllSneakers() {
        viewModelScope.launch {
            errorMessage = null
            isLoading = true
            try {

                val response = RetrofitProvider
                    .sneakersApi
                    .getAllSneakers()

                if(response.isSuccessful) {
                    response.body()?.let { sneakers ->
                        _sneakersPreviewList.value =
                            sneakers

                        if(sneakers.isEmpty()) {
                            isEmpty = true
                        }
                    }
                } else {
                    val gson = Gson()

                    val errorResponse =
                        gson.fromJson(
                            response.errorBody()?.string(),
                            MessageErrorResponse::class.java
                        )

                    errorMessage = errorResponse.error
                }
            } catch(_: Exception) {
                errorMessage = "Unknown error"
            } finally {
                isLoading = false
            }
        }
    }
}