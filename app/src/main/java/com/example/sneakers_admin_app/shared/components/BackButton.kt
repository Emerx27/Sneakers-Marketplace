package com.example.sneakers_admin_app.shared.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun BackButton(
    onClick: () -> Unit
) {
    var enabled by remember {
        mutableStateOf(true)
    }

    Icon(
        modifier = Modifier.clickable(
            enabled = enabled
        ) {
            enabled = false
            onClick()
        },
        imageVector = Icons.Default.ArrowBackIosNew,
        contentDescription = "Go back"
    )
}