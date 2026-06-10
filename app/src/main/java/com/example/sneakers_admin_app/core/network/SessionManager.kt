package com.example.sneakers_admin_app.core.network

import kotlinx.coroutines.flow.MutableStateFlow

object SessionManager {
    val isAuthenticated = MutableStateFlow<Boolean?>(null)
}