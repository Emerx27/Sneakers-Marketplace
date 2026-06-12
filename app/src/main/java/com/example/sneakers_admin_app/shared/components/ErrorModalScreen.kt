package com.example.sneakers_admin_app.shared.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun ErrorModalScreen(
    message: String,
    onDismiss: () -> Unit,
    actionText: String
) {

   Dialog(
       onDismissRequest = onDismiss
   ) {
       Column {
           Card(
               colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
               modifier = Modifier.fillMaxWidth(0.8f),
               shape = RoundedCornerShape(4.dp)
           ) {
               Column(
                   modifier = Modifier.padding(16.dp)
               ) {
                   Column(
                       modifier = Modifier.fillMaxWidth(),
                       verticalArrangement = Arrangement.spacedBy(12.dp),
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                       Text(text = "Error", fontWeight = FontWeight.Bold, fontSize = 22.sp)

                       Text(
                           text = message,
                           color = MaterialTheme.colorScheme.error,
                           textAlign = TextAlign.Center
                       )
                   }
               }

               Surface(
                   modifier = Modifier
                       .fillMaxWidth()
                       .clickable {onDismiss()},
                   color = MaterialTheme.colorScheme.error,
                   shape = RectangleShape
               ) {
                   Text(
                       modifier = Modifier.padding(12.dp),
                       textAlign = TextAlign.Center,
                       text = actionText
                   )
               }
           }

       }
   }
}