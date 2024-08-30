package com.vladzah.model

data class PhotoModel(
    val id: Int,
    val urlOrig: String,
    val urlComp: String,
    val width: Int,
    val height: Int,
    val photographer: String,
    val isBookmarked: Boolean
)