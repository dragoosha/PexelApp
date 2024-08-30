package com.vladzah.pexelapp.ui.components.imagesGrid

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.vladzah.pexelapp.models.PhotoUiModel

@Composable
fun ImagesGrid(
    photosList: LazyPagingItems<PhotoUiModel>
) {
    Box(modifier = Modifier) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(photosList.itemCount) {index ->
                photosList[index]?.let { photo ->
                    PhotoCard(photo = photo) {}
                }
                Log.d("CheckPhoto", "Iam in items")

            }
        }
    }
}

@Composable
fun PhotoCard(
    photo: PhotoUiModel,
    onclick: () -> Unit
) {
    val ratio = countSize(photo.width, photo.height)

    Box(
        modifier = Modifier
            .aspectRatio(ratio)
            .fillMaxWidth()
            .clickable { onclick() }
            .padding(12.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Log.d("CheckPhoto", "Iam in AsyncImage + ${photo.url}")

        AsyncImage(
            model = photo.url,
            contentDescription = photo.photographer,
            modifier = Modifier
                .fillMaxSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
        )

    }
}

fun countSize(width: Int, height: Int): Float {
    val ratio: Float = width.toFloat() / height

    return ratio
}