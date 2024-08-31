package com.vladzah.pexelapp.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.vladzah.pexelapp.models.PhotoUiModel
import com.vladzah.pexelapp.viewmodels.DetailsScreenViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    photoId: Int?,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
) {
    Text("$photoId")
}