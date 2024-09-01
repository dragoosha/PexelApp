package com.vladzah.pexelapp.ui.components.bars

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vladzah.pexelapp.R
import com.vladzah.pexelapp.ui.components.buttons.BookmarkButton
import com.vladzah.pexelapp.ui.components.buttons.DownloadButton
import com.vladzah.pexelapp.utils.Icons

@Composable
fun BottomBar (
    isBookmarked: Boolean,
    onDownloadClicked: () -> Unit,
    onBookmarkClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DownloadButton(
            title = stringResource(R.string.download),
            icon = Icons.DownloadIcon,
            onClick = onDownloadClicked
        )

        Spacer(Modifier.weight(1f))

        BookmarkButton(
            isBookmarked = isBookmarked,
            onClick = onBookmarkClicked
        )
    }
}