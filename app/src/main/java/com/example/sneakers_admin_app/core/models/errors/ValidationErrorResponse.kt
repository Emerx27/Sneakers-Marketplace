package com.example.sneakers_admin_app.core.models.errors

data class ValidationErrorResponse(
    val error: Map<String, String>
)