package com.example.sneakers_admin_app.features.sneakers.models.publish

data class PublishSneakerResponse(
    val id: Int,
    val brand: String,
    val model: String,
    val description: String?,
    val price: Double,
    val condition: String,
    val sku: String,
    val userId: Int
)