package com.vladzah.pexelapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vladzah.pexelapp.R

object Strings {
    val HomeString
        get() = R.string.home
    val BookmarkString
        get() = R.string.bookmark

    val ChatString
        get() = R.string.chat

    val Error400
        get() = R.string.error_400
    val Error401
        get() = R.string.error_401
    val Error403
        get() = R.string.error_403
    val Error404
        get() = R.string.error_404
    val Error500
        get() = R.string.error_500
    val ErrorUnknownHost
        get() = R.string.error_unknown_host
    val ErrorTimeout
        get() = R.string.error_timeout

    val BackButton
        get() = R.string.back_button

    val NoBookmark
        get() = R.string.no_bookmark
    val NoData
        get() = R.string.no_data_available
}