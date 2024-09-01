package com.vladzah.pexelapp.events

import com.vladzah.pexelapp.models.PhotoUiModel

sealed class DetailedScreenEvents {
    data class onInitFromHomeEvent(val id: Int) : DetailedScreenEvents()
    data class onInitFromBookmarkEvent(val id: Int) : DetailedScreenEvents()
    data class onDowloadClickEvent(val photoUiModel: PhotoUiModel): DetailedScreenEvents()
    data class onBookmarkClickEvent(val photoUiModel: PhotoUiModel): DetailedScreenEvents()
}