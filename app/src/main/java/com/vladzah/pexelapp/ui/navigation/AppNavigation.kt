package com.vladzah.pexelapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vladzah.pexelapp.models.PhotoUiModel
import com.vladzah.pexelapp.ui.screens.bookmarks.BookmarksScreen
import com.vladzah.pexelapp.ui.screens.chat.ChatScreen
import com.vladzah.pexelapp.ui.screens.details.DetailsScreen
import com.vladzah.pexelapp.ui.screens.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationItem.WithIcons.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavigationItem.WithIcons.Home.route) { HomeScreen(navController) }
            composable(NavigationItem.WithIcons.Bookmark.route) { BookmarksScreen(navController) }
            composable(NavigationItem.WithIcons.Chat.route) {ChatScreen(navController)}
            composable(
                route = "details/{id}/?source={source}",
                arguments = listOf(
                    navArgument("id") {type = NavType.IntType},
                    navArgument("source") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id")
                val source = backStackEntry.arguments?.getString("source")
                DetailsScreen(navController, id, source)
            }
        }
    }
}