package com.vladzah.pexelapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.vladzah.pexelapp.events.HomeScreenEvents
import com.vladzah.pexelapp.models.PhotoUiModel
import com.vladzah.pexelapp.models.TopicUiModel
import com.vladzah.pexelapp.ui.components.images.ImagesGrid
import com.vladzah.pexelapp.ui.components.bars.ProgressBar
import com.vladzah.pexelapp.ui.components.search.SearchBarComponent
import com.vladzah.pexelapp.ui.components.topics.TopicList
import com.vladzah.pexelapp.ui.navigation.NavigationItem
import com.vladzah.pexelapp.utils.Strings
import com.vladzah.pexelapp.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val photos = viewModel.photos.collectAsLazyPagingItems()
    val titles = viewModel.titles.collectAsState().value
    HomeScreenLayout(
        photos = photos,
        titles = titles,
        viewModel = viewModel,
        onNavigateClick = {photoUiModel ->
            navController.navigate("details/${photoUiModel.id}/?source=${NavigationItem.WithIcons.Home.route}")
        }
    )

}

@Composable
fun HomeScreenLayout(
    photos : LazyPagingItems<PhotoUiModel>,
    titles : List<TopicUiModel>,
    viewModel: HomeScreenViewModel,
    onNavigateClick: (PhotoUiModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(MaterialTheme.colors.background)
    ) {

        var query by remember {
            mutableStateOf("")
        }

        var isActiveSearch by remember { mutableStateOf(false) }

        SearchBarComponent(
            query = query,
            onQueryChange = { newData ->
                query = newData
                viewModel.onEvent(HomeScreenEvents.onNewQuery(query))
            },
            isActiveSearch = isActiveSearch,
            onActiveChange = { isActive -> isActiveSearch = isActive }
        )

        TopicList(
            list = titles,
            onClick = { topic ->
                query = topic.label
                viewModel.checkAndMoveTitle(query)
                viewModel.onEvent(HomeScreenEvents.onNewQuery(query))
            }
        )

        ProgressBar(
            isLoading = photos.loadState.refresh is LoadState.Loading
        )

        ImagesGrid(
            photosList = photos,
            errorMessage = stringResource(Strings.NoData),
            onExploreClick = {
                viewModel.onEvent(HomeScreenEvents.onExploreClicked)
            },
            onRetryClick = {
                viewModel.onEvent(HomeScreenEvents.onRetryClicked)
            },
            onPhotoClick = {photoUiModel ->
                onNavigateClick(photoUiModel)
            }
        )

    }
}
