package com.vladzah.pexelapp.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vladzah.pexelapp.ui.screens.bookmarks.BookmarksScreen
import com.vladzah.pexelapp.ui.screens.chat.ChatScreen
import com.vladzah.pexelapp.ui.screens.details.DetailsScreen
import com.vladzah.pexelapp.ui.screens.home.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation() {
    val navController = rememberAnimatedNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationItem.WithIcons.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                NavigationItem.WithIcons.Home.route,
                enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700)) },
                exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700)) }
            ) { HomeScreen(navController) }

            composable(
                NavigationItem.WithIcons.Bookmark.route,
                enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700)) },
                exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700)) }
            ) { BookmarksScreen(navController) }

            composable(
                NavigationItem.WithIcons.Chat.route,
                enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700)) },
                exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700)) }
            ) { ChatScreen(navController) }

            composable(
                route = "details/{id}/?source={source}",
                enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700)) },
                exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700)) },
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