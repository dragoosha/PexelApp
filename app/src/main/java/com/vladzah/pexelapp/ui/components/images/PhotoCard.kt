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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vladzah.pexelapp.models.PhotoUiModel

@Composable
fun PhotoCard(
    photo: PhotoUiModel,
    onclick: (PhotoUiModel) -> Unit
) {
    val ratio = countSize(photo.width, photo.height)

    Box(
        modifier = Modifier
            .aspectRatio(ratio)
            .fillMaxWidth()
            .clickable { onclick(photo) }
            .padding(12.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(20.dp)
            )
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

    }
}

fun countSize(width: Int, height: Int): Float {
    val ratio: Float = width.toFloat() / height

    return ratio
}