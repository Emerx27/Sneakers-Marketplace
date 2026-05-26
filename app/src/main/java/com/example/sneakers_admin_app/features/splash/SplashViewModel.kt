package com.example.sneakers_admin_app.features.splash

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakers_admin_app.core.storage.UserPreferences
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)
    private val _hasToken = mutableStateOf<Boolean?>(null)

    val hasToken = _hasToken

    init {
        viewModelScope.launch {
            _hasToken.value =
                userPreferences.getToken() != null
        }
    }
}