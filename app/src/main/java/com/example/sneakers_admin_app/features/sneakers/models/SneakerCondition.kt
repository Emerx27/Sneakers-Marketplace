package com.example.sneakers_admin_app.features.sneakers.models

enum class SneakerCondition(
    val value: String,
    val label: String
) {
    NEW("NEW", "New"),
    USED_LIKE_NEW("USED_LIKE_NEW", "Used - Like New"),
    USED_GOOD("USED_GOOD", "Used - Good"),
    USED_FAIR("USED_FAIR", "Used - Fair")
}