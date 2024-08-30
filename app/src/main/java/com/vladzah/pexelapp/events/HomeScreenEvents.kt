package com.vladzah.pexelapp.events

sealed class HomeScreenEvents {
    data class onNewQuery(val query: String): HomeScreenEvents()
    object onExploreClicked : HomeScreenEvents()
}