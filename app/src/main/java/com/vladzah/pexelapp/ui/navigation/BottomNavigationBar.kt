package com.vladzah.pexelapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vladzah.pexelapp.ui.theme.Black
import com.vladzah.pexelapp.ui.theme.Red


@Composable
fun BottomNavigationBar(
    navController: NavController
){
    val items = listOf(
        NavigationItem.WithIcons.Home,
        NavigationItem.WithIcons.Bookmark
    )

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.background
    ) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = item.iconInactive(),
                        contentDescription = stringResource(id = item.title)
                    )
                },
                selected = currentRoute == item.route,
                selectedContentColor = Red,
                unselectedContentColor = Black,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }

}

@Preview
@Composable
fun CheckBottomNavigationBar(){
    BottomNavigationBar(navController = rememberNavController())
}