package com.vladzah.pexelapp.events

sealed class DetailedScreenEvents {
    data class onInitFromHomeEvent(val id: Int) : DetailedScreenEvents()
    data class onInitFromBookmarkEvent(val id: Int) : DetailedScreenEvents()
}