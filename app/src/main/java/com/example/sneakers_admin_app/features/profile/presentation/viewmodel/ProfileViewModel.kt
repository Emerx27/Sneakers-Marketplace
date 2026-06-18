package com.example.sneakers_admin_app.features.profile.presentation.viewmodel

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
import com.example.sneakers_admin_app.features.profile.models.ProfileSneakerItem
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)

    var user by mutableStateOf<User?>(null)
        private set

    init {
        viewModelScope.launch {
            user =
                userPreferences.getUser()
        }
    }

    private val _activeSneakersList = MutableStateFlow<List<ProfileSneakerItem>>(emptyList())

    val activeSneakerList = _activeSneakersList.asStateFlow()

    var errorMessage by mutableStateOf<String?>(null)

    fun getSneakers() {
        viewModelScope.launch {
            try {
                val response = RetrofitProvider
                    .profileApi
                    .getMySneakers()

                if(response.isSuccessful) {
                    response.body()?.let { sneakers ->
                        _activeSneakersList.value =
                            sneakers
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
            } catch (_: Exception) {
                errorMessage = "Error loading active posts"
            }
        }
    }
}