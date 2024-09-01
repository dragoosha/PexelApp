package com.vladzah.pexelapp.ui.navigation

import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
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
            title = Strings.BookmarkString
        )

        object Chat: WithIcons(
            route = "chat",
            iconActive = {Icons.ChatActiveIcon},
            iconInactive = {Icons.ChatActiveIcon},
            title = Strings.ChatString
        )
    }
    object Details: NavigationItem("details")
}
