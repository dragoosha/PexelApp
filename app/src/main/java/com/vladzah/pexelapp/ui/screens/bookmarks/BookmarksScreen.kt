package com.vladzah.pexelapp.ui.screens.bookmarks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.vladzah.pexelapp.models.PhotoUiModel
import com.vladzah.pexelapp.ui.components.images.ImagesGrid
import com.vladzah.pexelapp.ui.navigation.NavigationItem
import com.vladzah.pexelapp.ui.theme.PexelAppTheme
import com.vladzah.pexelapp.viewmodels.BookmarksScreenViewModel

@Composable
fun BookmarksScreen(
    navController: NavHostController,
    viewModel: BookmarksScreenViewModel = hiltViewModel(),
) {

    val bookmarks = viewModel.bookmars.collectAsLazyPagingItems()

    BookmarksScreenLayout(
        photos = bookmarks,
        onNavigateClick = {photoUiModel ->
            navController
                .navigate(
                "details/${photoUiModel.id}/?source=${NavigationItem.WithIcons.Bookmark.route}"
            )
        }
    )

}

@Composable
fun BookmarksScreenLayout(
    photos: LazyPagingItems<PhotoUiModel>,
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

        ImagesGrid(
            photosList = photos,
            onExploreClick = { /*TODO*/ },
            onRetryClick = { /*TODO*/ },
            onPhotoClick = {photoUiModel -> onNavigateClick(photoUiModel) },
            isBookmarkScreen = true
        )

    }
}