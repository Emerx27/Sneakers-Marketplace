package com.example.sneakers_admin_app.features.auth.models.login

import com.example.sneakers_admin_app.core.models.User

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val user: User
)