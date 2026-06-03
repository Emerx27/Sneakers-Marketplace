package com.example.sneakers_admin_app.shared.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import com.example.sneakers_admin_app.ui.theme.AppColors

@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isError: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    errorText: String? = null,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        placeholder = {
            Text(
                text = placeholder,
                color = AppColors.TextMuted
            )
        },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        supportingText = errorText?.let { error ->
            {
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        maxLines = maxLines,
        colors = OutlinedTextFieldDefaults.colors(
            disabledTextColor = Color.White,
            disabledBorderColor = MaterialTheme.colorScheme.outline
        )
    )
}