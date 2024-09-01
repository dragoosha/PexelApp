package com.vladzah.pexelapp.events

sealed class ChatScreenEvents {
    data class onButtonSendEvent(val content: String) : ChatScreenEvents()
}