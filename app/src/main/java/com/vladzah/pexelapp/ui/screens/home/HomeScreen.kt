package com.vladzah.pexelapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vladzah.pexelapp.ui.components.imagesGrid.ImagesGrid
import com.vladzah.pexelapp.ui.components.imagesGrid.PhotoUi
import com.vladzah.pexelapp.ui.components.search.SearchBarComponent
import com.vladzah.pexelapp.ui.components.topics.TopicList
import com.vladzah.pexelapp.ui.components.topics.TopicUi
import com.vladzah.pexelapp.ui.theme.PexelAppTheme

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {

    HomeScreenLayout()

}

@Composable
fun HomeScreenLayout() {
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

        val listOfPhotos = listOf(
            PhotoUi(
                url = "https://images.pexels.com/photos/837358/pexels-photo-837358.jpeg",
                width = 6100,
                height = 4067,
                photographer = "Andrea Piacquadio"
            ),
            PhotoUi(
                url = "https://images.pexels.com/photos/39866/entrepreneur-startup-start-up-man-39866.jpeg",
                width = 4928,
                height = 3264,
                photographer = "Pixabay"
            ),
            PhotoUi(
                url = "https://images.pexels.com/photos/445109/pexels-photo-445109.jpeg",
                width = 7016,
                height = 4405,
                photographer = "Trinity  Kubassek"
            ),
            PhotoUi(
                url = "https://images.pexels.com/photos/1709003/pexels-photo-1709003.jpeg",
                width = 3600,
                height = 2400,
                photographer = "ICSA"
            )
        )

        ImagesGrid(photosList = listOfPhotos)

    }
}

@Preview
@Composable
fun PreviewHome () {
    PexelAppTheme(dynamicColor = false) {
        HomeScreen(navController = rememberNavController())
    }
}