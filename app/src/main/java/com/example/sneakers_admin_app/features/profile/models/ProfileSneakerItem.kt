package com.example.sneakers_admin_app.features.profile.models

data class ProfileSneakerItem(
    val id: Long,
    val brand: String,
    val model: String,
    val price: Double,
    val thumbnail: String
)
