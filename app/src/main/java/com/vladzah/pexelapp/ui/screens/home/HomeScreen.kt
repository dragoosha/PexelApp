package com.vladzah.pexelapp.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.vladzah.pexelapp.models.PhotoUiModel
import com.vladzah.pexelapp.ui.components.imagesGrid.ImagesGrid
import com.vladzah.pexelapp.ui.components.search.SearchBarComponent
import com.vladzah.pexelapp.ui.components.topics.TopicList
import com.vladzah.pexelapp.ui.components.topics.TopicUi
import com.vladzah.pexelapp.ui.theme.PexelAppTheme
import com.vladzah.pexelapp.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val photos = viewModel.photos.collectAsLazyPagingItems()
    HomeScreenLayout(photos)

}

@Composable
fun HomeScreenLayout(
    photos : LazyPagingItems<PhotoUiModel>
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

        SearchBarComponent(
            query = query,
            onQueryChange = { newData ->
                query = newData
            }
        )

        val listOfTopics = listOf(
            TopicUi ("ice", true),
            TopicUi ("twice", false),
            TopicUi ("photographer", false),
            TopicUi ("communication", false),
            TopicUi ("helpful", false),
        )

        TopicList(list = listOfTopics) {
            
        }

        val context = LocalContext.current
        LaunchedEffect(key1 = photos.loadState) {
            if (photos.loadState.refresh is LoadState.Error) {
                Toast.makeText(
                    context,
                    "Error:" + (photos.loadState.refresh as LoadState.Error).error.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        ImagesGrid(photosList = photos)

    }
}

@Preview
@Composable
fun PreviewHome () {
    PexelAppTheme(dynamicColor = false) {
        HomeScreen(navController = rememberNavController())
    }
}