package com.vladzah.pexelapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import com.vladzah.pexelapp.R

object Icons {
    val HomeInactive: Painter
        @Composable get() = painterResource(id = R.drawable.ic_home_inactive)
    val HomeActive: Painter
        @Composable get() = painterResource(id = R.drawable.ic_home_active)
    val BookMarkInactive: Painter
        @Composable get() = painterResource(id = R.drawable.ic_bookmark_inactive)
    val BookMarkActive: Painter
        @Composable get() = painterResource(id = R.drawable.ic_bookmark_active)

    val SearchIcon: Painter
        @Composable get() = painterResource(id = R.drawable.icon_search)

    val NoInternet: Painter
        @Composable get() = painterResource(id = R.drawable.ic_no_network)

    val BackArrow: Painter
        @Composable get() = painterResource(id = R.drawable.ic_back)

    val DownloadIcon: Painter
        @Composable get() = painterResource(id = R.drawable.ic_download)

    val ChatActiveIcon: Painter
        @Composable get() = rememberVectorPainter(image = Icons.Default.MailOutline)
}