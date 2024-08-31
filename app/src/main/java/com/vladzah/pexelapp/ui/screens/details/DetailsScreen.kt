package com.vladzah.pexelapp.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.vladzah.pexelapp.events.DetailedScreenEvents
import com.vladzah.pexelapp.models.PhotoUiModel
import com.vladzah.pexelapp.ui.components.bars.BottomBar
import com.vladzah.pexelapp.ui.components.bars.TopBar
import com.vladzah.pexelapp.ui.components.images.PhotoCard
import com.vladzah.pexelapp.ui.theme.PexelAppTheme
import com.vladzah.pexelapp.viewmodels.DetailsScreenViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    photoId: Int?,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
) {

    photoId?.let {
        DetailedScreenEvents.onInitEvent(it)
    }?.let {
        viewModel.onEvent(it)
    } ?: Stub()

    val photoModel = viewModel.photoModel.collectAsState().value
    photoModel?.let{
            DetailsScreenLayout(
                photoModel = photoModel,
                onNavigateClick = {}
            )
        }
}

@Composable
fun DetailsScreenLayout(
    photoModel: PhotoUiModel,
    onNavigateClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // TopBar remains fixed at the top
        TopBar(
            photographerName = photoModel.photographer,
            onNavigateClick = onNavigateClick
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                PhotoCard(
                    photo = photoModel,
                ) {
                }
            }

            item {
                BottomBar(
                )
            }
        }
    }

}

@Composable
fun Stub() {

}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    PexelAppTheme {
        DetailsScreenLayout(
            photoModel = PhotoUiModel(id = 2010201, "", 6100, 4067, "Calvin Clein")
        ) {

        }
    }
}