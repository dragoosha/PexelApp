package com.vladzah.pexelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.vladzah.pexelapp.ui.screens.main.MainScreen
import com.vladzah.pexelapp.ui.theme.PexelAppTheme
import com.vladzah.pexelapp.viewmodels.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainScreenViewModel: HomeScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createSplashScreen()

        setContent {
            PexelAppTheme {
                MainScreen()
            }
        }
    }

    private fun createSplashScreen() {
        installSplashScreen().apply {
            setKeepOnScreenCondition { mainScreenViewModel.isLoading.value }
        }
    }
}
