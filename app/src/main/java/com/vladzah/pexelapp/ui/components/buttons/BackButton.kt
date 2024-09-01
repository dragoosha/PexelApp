package com.vladzah.pexelapp.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vladzah.pexelapp.ui.theme.PexelAppTheme
import com.vladzah.pexelapp.utils.Icons

@Composable
fun BackButton(
    icon: Painter,
    onNavigateClick: () -> Unit
) {
    Icon(
        painter = icon,
        contentDescription = "Back Button",
        tint = MaterialTheme.colorScheme.onSecondaryContainer,
        modifier = Modifier
            .size(40.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable { onNavigateClick() }
            .padding(10.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun BackButtonPreview() {
    PexelAppTheme(dynamicColor = false) {
        BackButton(icon = Icons.BackArrow) {}
    }
}