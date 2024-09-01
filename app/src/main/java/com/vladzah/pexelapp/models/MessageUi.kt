package com.vladzah.pexelapp.models

data class MessageUi(
    val id: Int,
    val content: String,
    val timestamp: Long,
    val isOutgoing: Boolean,
    var isClosingType: Boolean
)
