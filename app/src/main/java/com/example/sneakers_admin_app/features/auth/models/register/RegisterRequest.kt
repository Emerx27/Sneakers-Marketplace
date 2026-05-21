package com.example.sneakers_admin_app.features.auth.models.register

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)