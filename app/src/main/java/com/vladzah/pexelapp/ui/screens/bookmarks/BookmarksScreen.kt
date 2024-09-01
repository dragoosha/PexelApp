package com.vladzah.pexelapp.ui.screens.bookmarks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.vladzah.pexelapp.models.PhotoUiModel
import com.vladzah.pexelapp.ui.components.bars.ProgressBar
import com.vladzah.pexelapp.ui.components.images.ImagesGrid
import com.vladzah.pexelapp.ui.navigation.NavigationItem
import com.vladzah.pexelapp.utils.Strings
import com.vladzah.pexelapp.viewmodels.BookmarksScreenViewModel

@Composable
fun BookmarksScreen(
    navController: NavHostController,
    viewModel: BookmarksScreenViewModel = hiltViewModel(),
) {

    val bookmarks = viewModel.bookmars.collectAsLazyPagingItems()
    val isLoading = viewModel.isLoading.collectAsState().value

    BookmarksScreenLayout(
        photos = bookmarks,
        isLoading = isLoading,
        onNavigateClick = {photoUiModel ->
            navController
                .navigate(
                "details/${photoUiModel.id}/?source=${NavigationItem.WithIcons.Bookmark.route}"
            )
        },
        onExploreClick = {
            navController.navigate(NavigationItem.WithIcons.Home.route)
        }
    )

}

@Composable
fun BookmarksScreenLayout(
    photos: LazyPagingItems<PhotoUiModel>,
    isLoading: Boolean,
    onExploreClick: () -> Unit,
    onNavigateClick: (PhotoUiModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bookmarks",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(vertical = 25.dp)
        )

        ProgressBar(isLoading = isLoading)

        ImagesGrid(
            photosList = photos,
            onExploreClick = onExploreClick,
            errorMessage = stringResource(Strings.NoBookmark),
            onRetryClick = {},
            onPhotoClick = {photoUiModel -> onNavigateClick(photoUiModel) },
            isBookmarkScreen = true
        )

    }
}