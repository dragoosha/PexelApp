package com.vladzah.pexelapp.ui.components.bars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vladzah.pexelapp.ui.components.buttons.BackButton
import com.vladzah.pexelapp.ui.theme.PexelAppTheme
import com.vladzah.pexelapp.utils.Icons

@Composable
fun TopBar(
    photographerName: String,
    onNavigateClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background
            )
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        BackButton(
            icon = Icons.BackArrow,
            onNavigateClick = onNavigateClick
        )

        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = photographerName,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground

        )
    }


}

@Preview(showBackground = true)
@Composable
fun TopBarPreviewLight() {
    PexelAppTheme {
        TopBar("James Arthur") {}
    }
}