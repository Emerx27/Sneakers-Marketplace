package com.example.sneakers_admin_app.shared.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakers_admin_app.ui.theme.AppColors

@Composable
fun SelectorField(
    value: String,
    placeholder: String,
    onClick: () -> Unit,
    isError: Boolean = false,
    errorText: String? = null
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    shape = RoundedCornerShape(4.dp),
                    width = 1.dp,
                    color =
                        if (isError)
                            MaterialTheme.colorScheme.error
                        else
                            MaterialTheme.colorScheme.outline,
                )
                .clickable(onClick = onClick)
                .padding(
                    horizontal = 16.dp,
                    vertical = 18.dp
                )
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text =
                        value.ifBlank { placeholder },
                    color =
                        if (value.isBlank())
                            AppColors.TextMuted
                        else
                            MaterialTheme.colorScheme.onSurface
                )

                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = AppColors.TextMuted
                )
            }
        }

        if (errorText != null) {
            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 4.dp
                ),
                text = errorText,
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp
            )
        }
    }
}