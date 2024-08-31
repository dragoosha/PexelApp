package com.vladzah.pexelapp.ui.components.imagesGrid

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.vladzah.pexelapp.R
import com.vladzah.pexelapp.models.PhotoUiModel
import com.vladzah.pexelapp.ui.components.buttons.ExploreButton
import com.vladzah.pexelapp.utils.Strings

@Composable
fun ImagesGrid(
    photosList: LazyPagingItems<PhotoUiModel>,
    onExploreClick: () -> Unit
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
            }
        }

        NoDataStub(
            photosList = photosList,
            onExploreClick = onExploreClick
        )


    }
}

@Composable
fun NoDataStub(
    photosList: LazyPagingItems<PhotoUiModel>,
    onExploreClick: () -> Unit,
) {
    when {
        photosList.loadState.refresh is LoadState.NotLoading && photosList.itemCount == 0 -> {

            val context = LocalContext.current
            LaunchedEffect(key1 = photosList.loadState){
                if(photosList.loadState.refresh is LoadState.Error) {
                    val error = (photosList.loadState.refresh as LoadState.Error).error
                    val errorMessage = when (error) {
                        is retrofit2.HttpException -> when (error.code()) {
                            400 -> Strings.Error400
                            401 -> Strings.Error401
                            403 -> Strings.Error403
                            404 -> Strings.Error404
                            500 -> Strings.Error500
                            else -> "HTTP Error: ${error.message()}"
                        }
                        is java.net.UnknownHostException -> Strings.ErrorUnknownHost
                        is java.net.SocketTimeoutException -> Strings.ErrorTimeout
                        else -> "Unexpected Error: ${error.message ?: "Unknown error"}"
                    }

                    Toast.makeText(context, "Error: $errorMessage", Toast.LENGTH_LONG).show()
                    Log.e("PhotosLoadError", "Error loading photos: $errorMessage")
                }
            }

            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(R.string.no_data_available))
                Spacer(Modifier.height(12.dp))
                ExploreButton(text = stringResource(R.string.explore), onClick = onExploreClick)
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