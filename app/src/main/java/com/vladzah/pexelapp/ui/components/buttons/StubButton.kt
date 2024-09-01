package com.vladzah.pexelapp.ui.components.buttons

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun StubButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary),
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
        )
    }
}