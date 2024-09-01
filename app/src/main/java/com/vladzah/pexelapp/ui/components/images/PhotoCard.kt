package com.vladzah.pexelapp.ui.components.images

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vladzah.pexelapp.models.PhotoUiModel

@Composable
fun PhotoCard(
    modifier: Modifier,
    photo: PhotoUiModel,
    isBookmarkScreen : Boolean = false,
    onclick: (PhotoUiModel) -> Unit,
) {
    val ratio = countSize(photo.width, photo.height)

    Box(
        modifier = modifier
            .aspectRatio(ratio)
            .fillMaxWidth()
            .clickable { onclick(photo) }
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(shape = RoundedCornerShape(20.dp))
    ) {

        AsyncImage(
            model = photo.url,
            contentDescription = photo.photographer,
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    shape = RoundedCornerShape(20.dp)
                ),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
        )

        if (isBookmarkScreen) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black.copy(alpha = 0.4f))
                    .padding(8.dp)
                    .clip(
                        shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                    )
                    .align(Alignment.BottomCenter),

                text = photo.photographer,
                textAlign = TextAlign.Center,
                color = Color.White,
            )
        }

    }

}

fun countSize(width: Int, height: Int): Float {
    val ratio: Float = width.toFloat() / height

    return ratio
}