package com.example.sneakers_admin_app.features.sneakers.models

data class SneakerDetailResponse(
    val id: Long,
    val brand: String,
    val model: String,
    val sku: String,
    val price: Double,
    val description: String?,
    val firstName: String,
    val lastName: String
)