package com.vladzah.pexelapp.ui.screens.details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vladzah.pexelapp.events.DetailedScreenEvents
import com.vladzah.pexelapp.models.PhotoUiModel
import com.vladzah.pexelapp.ui.components.bars.BottomBar
import com.vladzah.pexelapp.ui.components.bars.ProgressBar
import com.vladzah.pexelapp.ui.components.bars.TopBar
import com.vladzah.pexelapp.ui.components.images.PhotoCard
import com.vladzah.pexelapp.ui.navigation.NavigationItem
import com.vladzah.pexelapp.ui.theme.PexelAppTheme
import com.vladzah.pexelapp.viewmodels.DetailsScreenViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    photoId: Int?,
    source: String?,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
) {

    photoId?.let {id ->
        when (source){
            NavigationItem.WithIcons.Home.route -> {
                Log.d("Unique", "$id")
                viewModel.onEvent(DetailedScreenEvents.onInitFromHomeEvent(id))
            }

            NavigationItem.WithIcons.Bookmark.route -> {
                viewModel.onEvent(DetailedScreenEvents.onInitFromBookmarkEvent(id))
            }
            else -> {}
        }

    }

    val photoModel = viewModel.photoModel.collectAsState().value
    val isLoadingData = viewModel.isLoading.collectAsState().value


    if (photoModel != null) {
        DetailsScreenLayout(
            isLoadingData = isLoadingData,
            photoModel = photoModel,
            onNavigateClick = {
                navController.popBackStack()
            }
        )
    } else {
        Stub()
    }
}

@Composable
fun DetailsScreenLayout(
    isLoadingData : Boolean,
    photoModel: PhotoUiModel,
    onNavigateClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(
            photographerName = photoModel.photographer,
            onNavigateClick = onNavigateClick
        )

        ProgressBar(
            isLoading = isLoadingData
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                PhotoCard(
                    modifier = Modifier
                        .padding(24.dp),
                    photo = photoModel,
                ) {
                }
            }

            item {
                BottomBar(
                    isBookmarked = photoModel.isBookmarked
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
            photoModel = PhotoUiModel(id = 2010201, "", 6100, 4067, "Calvin Clein", true),
            isLoadingData = false
        ) {

        }
    }
}