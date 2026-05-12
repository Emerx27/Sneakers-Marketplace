package com.example.sneakers_admin_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sneakers_admin_app.ui.theme.Products_AdminTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Products_AdminTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SneakersAdminApp(padding = innerPadding)
                }
            }
        }
    }
}

@Composable
fun SneakersAdminApp(modifier: Modifier = Modifier, padding: PaddingValues) {
    Column(modifier = modifier.padding(padding)) {
        Text("Hello World!")
    }
}
