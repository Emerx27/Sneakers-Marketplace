package com.example.sneakers_admin_app.features.sneakers.models.publish

data class PublishSneakerRequest(
    val brand: String,
    val model: String,
    val sku: String,
    val price: Double,
    val condition: String,
    val description: String?,
)