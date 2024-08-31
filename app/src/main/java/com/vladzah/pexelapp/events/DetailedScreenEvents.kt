package com.vladzah.pexelapp.events

sealed class DetailedScreenEvents {
    data class onInitEvent(val id: Int) : DetailedScreenEvents()
}