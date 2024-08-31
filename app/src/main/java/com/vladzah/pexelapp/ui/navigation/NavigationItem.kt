package com.vladzah.pexelapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.vladzah.pexelapp.utils.Icons
import com.vladzah.pexelapp.utils.Strings

sealed class NavigationItem(val route: String) {
    sealed class WithIcons(
        route: String,
        val iconActive: @Composable () -> Painter,
        val iconInactive: @Composable () -> Painter,
        val title: Int
    ) : NavigationItem(route) {
        object Home: WithIcons(
            route = "home",
            iconActive = { Icons.HomeActive },
            iconInactive = { Icons.HomeInactive },
            title = Strings.HomeString
        )
        object Bookmark: WithIcons(
            route = "bookmark",
            iconActive = { Icons.BookMarkActive },
            iconInactive = { Icons.BookMarkInactive },
            title = Strings.HomeString
        )
    }
    object Details: NavigationItem("details")
}
