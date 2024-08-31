package com.vladzah.pexelapp.ui.screens.details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            },
            onDownloadClick = {photoUiModel ->
                viewModel.onEvent(DetailedScreenEvents.onDowloadClickEvent(photoUiModel))
            },
            onBookmarkClick = {photoUiModel ->
                viewModel.onEvent(DetailedScreenEvents.onBookmarkClickEvent(photoUiModel))
            }
        )
    } else {
        Stub(
            onNavigateClick = {
                navController.popBackStack()
            },
            onExploreClick = {
                navController.navigate(NavigationItem.WithIcons.Home.route)
            }
        )
    }
}

@Composable
fun DetailsScreenLayout(
    isLoadingData : Boolean,
    photoModel: PhotoUiModel,
    onNavigateClick: () -> Unit,
    onDownloadClick: (PhotoUiModel) -> Unit,
    onBookmarkClick: (PhotoUiModel) -> Unit
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
                    isBookmarked = photoModel.isBookmarked,
                    onDownloadClicked = { onDownloadClick(photoModel) },
                    onBookmarkClicked = { onBookmarkClick(photoModel) }
                )
            }
        }
    }

}

@Composable
fun Stub(
    onNavigateClick: () -> Unit,
    onExploreClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBar(
            photographerName = "",
            onNavigateClick = onNavigateClick
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text= "Image not found",
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier
                    .clickable { onExploreClick() },
                text = "Explore",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary,

            )
        }

    }




}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    PexelAppTheme {
        val model = PhotoUiModel(id = 2010201, "", 6100, 4067, "Calvin Clein", true)
        DetailsScreenLayout(
            photoModel = model,
            isLoadingData = false,
            onDownloadClick = {},
            onNavigateClick = {},
            onBookmarkClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StubPreview() {
    PexelAppTheme {
        Stub(
            onNavigateClick = {}
        ) {

        }
    }
}