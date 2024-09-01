package com.vladzah.pexelapp.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vladzah.pexelapp.ui.theme.PexelAppTheme
import com.vladzah.pexelapp.utils.Icons

@Composable
fun DownloadButton(
    title: String,
    icon: Painter,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(48.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = CircleShape
            )
            .clip(CircleShape)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp).background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = CircleShape
                )
                .padding(14.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                contentDescription = null
            )
        }

        Spacer(Modifier.width(17.dp))

        Text(
            text = title,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.width(37.dp))
        
    }

}

@Preview(showBackground = true)
@Composable
fun DownloadButtonPreview() {
    PexelAppTheme {
        DownloadButton(title = "Download", icon = Icons.DownloadIcon) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DownloadButtonPreviewNight() {
    PexelAppTheme(darkTheme = true) {
        DownloadButton(title = "Download", icon = Icons.DownloadIcon) {

        }
    }
}