package com.example.sneakers_admin_app.features.auth.models.login

import com.example.sneakers_admin_app.core.models.User

data class LoginResponse(
    val token: String,
    val user: User
)